package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.UserDto
import io.reactivex.Single

interface UserInfoRepository {
   fun getUserInformation(): Single<UserDto>
}