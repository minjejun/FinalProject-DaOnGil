package kr.tekit.lion.daongil.presentation.concerntype.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kr.tekit.lion.daongil.R
import kr.tekit.lion.daongil.databinding.FragmentConcernTypeModifyBinding

class ConcernTypeModifyFragment : Fragment(R.layout.fragment_concern_type_modify) {

    private val selectedConcernType = mutableSetOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentConcernTypeModifyBinding.bind(view)

        initView(binding)
        initSelection(binding)
        concernTypeModify(binding)
    }

    private fun initView(binding: FragmentConcernTypeModifyBinding) {
        with(binding) {
            toolbarConcernTypeModify.setNavigationIcon(R.drawable.back_icon)
            toolbarConcernTypeModify.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_concernTypeModifyFragment_to_concernTypeFragment)
            }

            imageViewConcernTypeModifyPhysical.setOnClickListener {
                toggleSelection(it as ImageView, R.drawable.physical_no_select, R.drawable.physical_select, binding)
            }
            imageViewConcernTypeModifyVisual.setOnClickListener {
                toggleSelection(it as ImageView, R.drawable.visual_no_select, R.drawable.visual_select, binding)
            }
            imageViewConcernTypeModifyHearing.setOnClickListener {
                toggleSelection(it as ImageView, R.drawable.hearing_no_select, R.drawable.hearing_select, binding)
            }
            imageViewConcernTypeModifyInfant.setOnClickListener {
                toggleSelection(it as ImageView, R.drawable.infant_family_no_select, R.drawable.infant_family_select, binding)
            }
            imageViewConcernTypeModifyElderly.setOnClickListener {
                toggleSelection(it as ImageView, R.drawable.elderly_people_no_select, R.drawable.elderly_people_select, binding)
            }
        }
    }

    private fun initSelection(binding: FragmentConcernTypeModifyBinding) {
        val userConcernType = listOf("지체장애", "시각장애")

        userConcernType.forEach { concernType ->
            when (concernType) {
                "지체장애" -> settingSelected(binding.imageViewConcernTypeModifyPhysical, R.drawable.physical_select)
                "시각장애" -> settingSelected(binding.imageViewConcernTypeModifyVisual, R.drawable.visual_select)
                "청각장애" -> settingSelected(binding.imageViewConcernTypeModifyHearing, R.drawable.hearing_select)
                "영유아 가족" -> settingSelected(binding.imageViewConcernTypeModifyInfant, R.drawable.infant_family_select)
                "고령자" -> settingSelected(binding.imageViewConcernTypeModifyElderly, R.drawable.elderly_people_select)
            }
        }

        updateModifyButtonState(binding)
    }

    private fun settingSelected(imageView: ImageView, selectedDrawable: Int) {
        imageView.setImageResource(selectedDrawable)
        imageView.tag = "true"
        selectedConcernType.add(imageView.id)
    }

    private fun toggleSelection(imageView: ImageView, unselectedDrawable: Int, selectedDrawable: Int, binding: FragmentConcernTypeModifyBinding) {
        if (imageView.tag == "true") {
            imageView.setImageResource(unselectedDrawable)
            imageView.tag = "false"
            selectedConcernType.remove(imageView.id)
        } else {
            imageView.setImageResource(selectedDrawable)
            imageView.tag = "true"
            selectedConcernType.add(imageView.id)
        }

        updateModifyButtonState(binding)
    }

    private fun updateModifyButtonState(binding: FragmentConcernTypeModifyBinding) {
        binding.buttonConcernTypeModify.isEnabled = selectedConcernType.isNotEmpty()
    }

    private fun concernTypeModify(binding: FragmentConcernTypeModifyBinding) {
        binding.buttonConcernTypeModify.setOnClickListener {
            showSnackbar(binding, "관심 유형이 수정되었습니다.")
            findNavController().navigate(R.id.action_concernTypeModifyFragment_to_concernTypeFragment)
        }
    }

    private fun showSnackbar(binding: FragmentConcernTypeModifyBinding, message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.text_secondary))
            .show()
    }
}