package com.example.data.mapper

import com.example.data.remote.response.MateResponse
import com.example.domain.entity.match.MateResponseEntity

object MatchMapper {
    fun mapperToMateEntity(mateResponse: MateResponse): MateResponseEntity =
        mateResponse.run { MateResponseEntity(me, opponent, myImage, opponentImage) }
}