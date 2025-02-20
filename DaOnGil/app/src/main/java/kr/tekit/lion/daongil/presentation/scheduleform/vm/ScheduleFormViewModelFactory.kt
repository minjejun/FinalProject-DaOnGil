package kr.tekit.lion.daongil.presentation.scheduleform.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.tekit.lion.daongil.domain.repository.BookmarkRepository
import kr.tekit.lion.daongil.domain.repository.PlaceRepository
import kr.tekit.lion.daongil.domain.repository.PlanRepository
import kr.tekit.lion.daongil.domain.usecase.plan.AddNewPlanUseCase
import kr.tekit.lion.daongil.domain.usecase.place.GetPlaceBookmarkListUseCase
import kr.tekit.lion.daongil.domain.usecase.place.GetPlaceDetailInfoUseCase
import kr.tekit.lion.daongil.domain.usecase.place.GetPlaceSearchResultUseCase

// ViewModelFactory = ViewModel을 생성하는 역할을 하는 클래스
// ViewModelProvider.Factory 인터페이스를 구현한다.
class ScheduleFormViewModelFactory : ViewModelProvider.Factory {
    private val getPlaceSearchResultUseCase = GetPlaceSearchResultUseCase(
        PlanRepository.create() // repository의 인스턴스를 생성하여 전달
    )
    private val getPlaceDetailInfoUseCase = GetPlaceDetailInfoUseCase(
        PlaceRepository.create()
    )
    private val addNewPlanUseCase = AddNewPlanUseCase(
        PlanRepository.create()
    )

    private val getPlaceBookmarkListUseCase = GetPlaceBookmarkListUseCase(
        BookmarkRepository.create()
    )

    // create : 특정 ViewModel 클래스를 인스턴스화하는 역할
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScheduleFormViewModel::class.java)){
            return ScheduleFormViewModel(
                getPlaceSearchResultUseCase,
                getPlaceDetailInfoUseCase,
                addNewPlanUseCase,
                getPlaceBookmarkListUseCase
            ) as T
        }
        throw IllegalArgumentException("unknown ViewModel Class")
    }
}