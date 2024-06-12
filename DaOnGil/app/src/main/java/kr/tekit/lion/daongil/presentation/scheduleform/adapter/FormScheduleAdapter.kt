package kr.tekit.lion.daongil.presentation.scheduleform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.tekit.lion.daongil.databinding.ItemFormScheduleBinding
import kr.tekit.lion.daongil.domain.model.DailySchedule

class FormScheduleAdapter(
    private val dailyScheduleList: List<DailySchedule>,
    private val onAddButtonClickListener : (schedulePosition: Int) -> Unit,
    private val onRemoveButtonClickListener : (schedulePosition: Int, placePosition: Int) -> Unit
) : RecyclerView.Adapter<FormScheduleAdapter.FormScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FormScheduleViewHolder(
            ItemFormScheduleBinding.inflate(inflater, parent, false),
            onAddItemClick = { schedulePosition ->
                // '여행지 추가' 버튼 클릭리스너 -> 다음 화면으로 이동시켜준다. (스케쥴 position 전달)
                this.onAddButtonClickListener(schedulePosition)
            },
            onRemoveButtonClickListener
        )
    }

    override fun onBindViewHolder(holder: FormScheduleViewHolder, position: Int) {
        holder.bind(dailyScheduleList[position])
    }

    override fun getItemCount(): Int {
        return dailyScheduleList.size
    }

    class FormScheduleViewHolder(
        private val binding: ItemFormScheduleBinding,
        private val onAddItemClick : (schedulePosition: Int) -> Unit,
        private val onRemoveButtonClickListener : (schedulePosition: Int, placePosition: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dailySchedule: DailySchedule) {
            binding.textViewFScheduleDate.text = dailySchedule.dailyDate

            // 추가할 여행지목록 Adapter
            val formPlaceAdapter = FormPlaceAdapter(dailySchedule.dailyPlaces, layoutPosition, onRemoveButtonClickListener)
            binding.recyclerViewFSchedulePlaces.adapter = formPlaceAdapter

            // 여행지 추가 버튼
            binding.buttonFScheduleAddPlace.setOnClickListener {
                onAddItemClick(layoutPosition)
            }
        }
    }
}