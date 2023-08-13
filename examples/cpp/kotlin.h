#pragma once

#include "personalrating_api.h"

#define LIB personalrating_symbols()
#define KN (LIB)->kotlin.root

#define KN_MODEL KN.model
#define KN_RemoteExpectValue KN_MODEL.RemoteExpectValue

#define KN_SERVICE KN.service
#define KN_RemoteDataService KN_SERVICE.RemoteDataServiceNative
#define KN_RemoteDataService_Ref \
    personalrating_kref_service_RemoteDataServiceNative
    
#define KN_USECASE KN.usecase
#define KN_PersonalRatingReader KN_USECASE.PersonalRatingReader
#define KN_PersonalRatingReader_Companion \
    personalrating_kref_usecase_PersonalRatingReader_Companion
#define KN_PersonalRatingReader_Ref \
    personalrating_kref_usecase_PersonalRatingReader
