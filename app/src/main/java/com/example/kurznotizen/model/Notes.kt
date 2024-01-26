package com.example.kurznotizen.model




import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "NOTES")
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,

    val body:String

): Parcelable
