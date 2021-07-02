package com.example.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")

class RoomMemo {

    @PrimaryKey(autoGenerate = true) // if no value for NO, 자동증가된 숫자값을 db에 입력
    @ColumnInfo

    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    constructor(content:String, datetime:Long){
        this.content = content
        this.datetime = datetime
    }
}