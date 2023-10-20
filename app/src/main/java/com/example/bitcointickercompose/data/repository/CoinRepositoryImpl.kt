package com.example.bitcointickercompose.data.repository

import androidx.room.withTransaction
import com.example.bitcointickercompose.data.mappers.toCoinDetailUI
import com.example.bitcointickercompose.data.mappers.toCoinListEntity
import com.example.bitcointickercompose.data.mappers.toCoinListUI
import com.example.bitcointickercompose.data.mappers.toCoinMarketsEntity
import com.example.bitcointickercompose.data.mappers.toCoinMarketsUI
import com.example.bitcointickercompose.data.source.local.CoinsRoomDB
import com.example.bitcointickercompose.domain.model.CoinDetail
import com.example.bitcointickercompose.domain.model.CoinList
import com.example.bitcointickercompose.domain.model.CoinMarkets
import com.example.bitcointickercompose.domain.repository.CoinRepository
import com.example.bitcointickercompose.domain.source.LocalDataSource
import com.example.bitcointickercompose.domain.source.RemoteDataSource
import com.example.bitcointickercompose.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Named
import kotlin.time.Duration

class CoinRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coinsRoomDB: CoinsRoomDB,
    @Named("Default") private val coroutineContextDefault: CoroutineDispatcher
) : CoinRepository {

    private var job: Job? = null

    override fun coinsMarkets(): Flow<Resource<List<CoinMarkets>>> = flow {
        emit(Resource.Loading())
        val cache = localDataSource.getCoinMarkets()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toCoinMarketsUI()))
        }
        val response = try {
            remoteDataSource.getCoinMarkets()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable.message ?: "Error!"))
            null
        }
        response?.let { data ->
            coinsRoomDB.withTransaction {
                localDataSource.deleteCoinMarkets()
                localDataSource.insertCoinMarketsList(data.toCoinMarketsEntity())
            }
            emit(Resource.Success(localDataSource.getCoinMarkets().toCoinMarketsUI()))
        }
    }

    override fun coinList(): Flow<Resource<List<CoinList>>> = flow {
        emit(Resource.Loading())

        val cache = localDataSource.getCoinList()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toCoinListUI()))
        }
        val response = try {
            remoteDataSource.getCoinList()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable.message ?: "Error!"))
            null
        }

        response?.let { data ->
            localDataSource.deleteCoinList()
            localDataSource.insertCoinList(data.toCoinListEntity())
        }
        emit(Resource.Success(data = localDataSource.getCoinList().toCoinListUI()))
    }

    override fun searchCoin(searchQuery: String): Flow<Resource<List<CoinList>>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(data = localDataSource.searchCoin(searchQuery).toCoinListUI()))
    }.catch {
        emit(Resource.Error(it.message ?: "Error!"))
    }

    override fun coinById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(data = remoteDataSource.getCoinById(coinId).toCoinDetailUI()))
    }.catch {
        emit(Resource.Error(it.message ?: "Error!"))
    }

    override fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>> =
        channelFlow<Resource<Double>> {
            job?.cancel()
            job = CoroutineScope(coroutineContextDefault).launch {
                while (true) {
                    send(Resource.Loading())
                    val data = remoteDataSource.getCoinById(coinId).toCoinDetailUI()
                    data.currentPrice?.let {
                        send(Resource.Success(it))
                    }
                    delay(period)
                }
            }
            awaitClose {
                channel.close()
                job?.cancel()
            }
        }.catch {
            emit(Resource.Error(it.message ?: "Error!"))
        }
}
