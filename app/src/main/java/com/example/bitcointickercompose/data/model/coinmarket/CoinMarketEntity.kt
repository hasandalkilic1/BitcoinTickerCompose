package com.example.bitcointickercompose.data.model.coinmarket

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "coin_markets")
data class CoinMarketEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "coinId")
    val coinId: String?,

    @ColumnInfo(name = "ath")
    val ath: Double?,

    @ColumnInfo(name = "priceType")
    val athChangePercentage: Double?,

    @ColumnInfo(name = "date")
    val athDate: String?,

    @ColumnInfo(name = "atl")
    val atl: Double?,

    @ColumnInfo(name = "atlChangePercentage")
    val atlChangePercentage: Double?,

    @ColumnInfo(name = "atlDate")
    val atlDate: String?,

    @ColumnInfo(name = "circulatingSupply")
    val circulatingSupply: Double?,

    @ColumnInfo(name = "currentPrice")
    val currentPrice: Double?,

    @ColumnInfo(name = "fullyDilutedValuation")
    val fullyDilutedValuation: Long?,

    @ColumnInfo(name = "high24h")
    val high24h: Double?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: String?,

    @ColumnInfo(name = "low24h")
    val low24h: Double?,

    @ColumnInfo(name = "marketCap")
    val marketCap: Long?,

    @ColumnInfo(name = "marketCapChange24h")
    val marketCapChange24h: Double?,

    @ColumnInfo(name = "marketCapChangePercentage24h")
    val marketCapChangePercentage24h: Double?,

    @ColumnInfo(name = "marketCapRank")
    val marketCapRank: Int?,

    @ColumnInfo(name = "maxSupply")
    val maxSupply: Double?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "priceChange24h")
    val priceChange24h: Double?,

    @ColumnInfo(name = "priceChangePercentage24h")
    val priceChangePercentage24h: Double?,

    @ColumnInfo(name = "symbol")
    val symbol: String?,

    @ColumnInfo(name = "totalSupply")
    val totalSupply: Double?,

    @ColumnInfo(name = "totalVolume")
    val totalVolume: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(coinId)
        parcel.writeValue(ath)
        parcel.writeValue(athChangePercentage)
        parcel.writeString(athDate)
        parcel.writeValue(atl)
        parcel.writeValue(atlChangePercentage)
        parcel.writeString(atlDate)
        parcel.writeValue(circulatingSupply)
        parcel.writeValue(currentPrice)
        parcel.writeValue(fullyDilutedValuation)
        parcel.writeValue(high24h)
        parcel.writeString(image)
        parcel.writeString(lastUpdated)
        parcel.writeValue(low24h)
        parcel.writeValue(marketCap)
        parcel.writeValue(marketCapChange24h)
        parcel.writeValue(marketCapChangePercentage24h)
        parcel.writeValue(marketCapRank)
        parcel.writeValue(maxSupply)
        parcel.writeString(name)
        parcel.writeValue(priceChange24h)
        parcel.writeValue(priceChangePercentage24h)
        parcel.writeString(symbol)
        parcel.writeValue(totalSupply)
        parcel.writeValue(totalVolume)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinMarketEntity> {
        override fun createFromParcel(parcel: Parcel): CoinMarketEntity {
            return CoinMarketEntity(parcel)
        }

        override fun newArray(size: Int): Array<CoinMarketEntity?> {
            return arrayOfNulls(size)
        }
    }
}