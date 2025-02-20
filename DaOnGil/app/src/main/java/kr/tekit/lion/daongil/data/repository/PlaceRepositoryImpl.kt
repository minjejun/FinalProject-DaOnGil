package kr.tekit.lion.daongil.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.tekit.lion.daongil.data.datasource.PlaceDataSource
import kr.tekit.lion.daongil.data.dto.remote.request.toRequestModel
import kr.tekit.lion.daongil.data.dto.remote.response.searchplace.list.toDomainModel
import kr.tekit.lion.daongil.domain.model.PlaceDetailInfo
import kr.tekit.lion.daongil.domain.model.ListSearchOption
import kr.tekit.lion.daongil.domain.model.ListSearchResult
import kr.tekit.lion.daongil.domain.model.MapSearchOption
import kr.tekit.lion.daongil.domain.model.MapSearchResult
import kr.tekit.lion.daongil.domain.model.MyPlaceReview
import kr.tekit.lion.daongil.domain.model.PlaceDetailInfoGuest
import kr.tekit.lion.daongil.domain.model.PlaceMainInfo
import kr.tekit.lion.daongil.domain.model.PlaceReviewInfo
import kr.tekit.lion.daongil.domain.model.SearchPlace
import kr.tekit.lion.daongil.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val placeDataSource: PlaceDataSource
): PlaceRepository {

    override suspend fun getPlaceDetailInfo(placeId: Long): PlaceDetailInfo {
        return placeDataSource.getPlaceDetailInfo(placeId).toDomainModel()
    }

    override suspend fun getSearchPlaceResultByList(request: ListSearchOption): List<ListSearchResult> {
        val response = placeDataSource.searchPlaceByList(request.toRequestModel())
        if (request.page == response.data.totalPages){
            return response.toDomainModel().map { it.copy(isLastPage = true) }
        }
        return response.toDomainModel()
    }

    override fun getSearchPlaceResultForMap(request: MapSearchOption): Flow<List<MapSearchResult>> {
        val response = placeDataSource.searchPlaceByMap(request.toRequestModel())
        return response.map { it.toDomainModel() }
    }

    override suspend fun getPlaceDetailInfoGuest(placeId: Long): PlaceDetailInfoGuest {
        return placeDataSource.getPlaceDetailInfoGuest(placeId).toDomainModel()
    }

    override suspend fun getPlaceMainInfo(areaCode: String, sigunguCode: String): PlaceMainInfo {
        return placeDataSource.getPlaceMainInfo(areaCode, sigunguCode).toDomainModel()
    }

    override suspend fun getPlaceReviewList(placeId: Long, page: Int, size: Int): PlaceReviewInfo {
        return placeDataSource.getPlaceReviewList(placeId, page, size).toDomainModel()
    }
    
    override suspend fun getMyPlaceReview(size: Int, page: Int): List<MyPlaceReview> {
        return placeDataSource.getMyPlaceReview(size, page).toDomainModel()
    }
}