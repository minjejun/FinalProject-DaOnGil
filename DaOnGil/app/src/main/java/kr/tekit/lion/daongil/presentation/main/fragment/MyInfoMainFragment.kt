package kr.tekit.lion.daongil.presentation.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kr.tekit.lion.daongil.R
import kr.tekit.lion.daongil.databinding.FragmentMyInfoMainBinding
import kr.tekit.lion.daongil.presentation.bookmark.BookmarkActivity
import kr.tekit.lion.daongil.presentation.concerntype.ConcernTypeActivity
import kr.tekit.lion.daongil.presentation.main.customview.ConfirmDialog
import kr.tekit.lion.daongil.presentation.main.customview.ConfirmDialogInterface
import kr.tekit.lion.daongil.presentation.myinfo.DeleteUserActivity
import kr.tekit.lion.daongil.presentation.myinfo.MyInfoActivity
import kr.tekit.lion.daongil.presentation.myreview.MyReviewActivity

class MyInfoMainFragment : Fragment(R.layout.fragment_my_info_main), ConfirmDialogInterface {

    private var originalStatusBarColor: Int? = null
    private var isUser = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMyInfoMainBinding.bind(view)

        initView(binding)
        changeStatusBarColor()
    }

    private fun initView(binding: FragmentMyInfoMainBinding) {
        if(isUser) {
            with(binding) {
                textViewMyInfoMainUserNickname.text = "김사자"
                moveMyInfo(binding)
                logoutDialog(binding)
                moveConcernType(binding)
                moveBookmark(binding)
                moveMyReview(binding)
                moveDeleteUser(binding)
            }
        } else {
            with(binding) {
                textViewMyInfoMainUserNickname.text = "아직 정보가 없어요!"
                textViewMyInfoMainUserTitle.isVisible = false
                textViewMyInfoMainReview.text = "로그인을 진행해주세요"
                textViewMyInfoMainReviewCnt.isVisible = false
            }
        }
    }

    private fun changeStatusBarColor() {
        activity?.let {
            originalStatusBarColor = it.window.statusBarColor

            it.window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.profile_background_endColor)
        }
    }

    private fun restoreStatusBarColor() {
        activity?.let {
            originalStatusBarColor?.let { color ->
                it.window.statusBarColor = color
            }
        }
    }

    private fun moveMyInfo(binding: FragmentMyInfoMainBinding) {
        binding.layoutProfile.setOnClickListener {
            val intent = Intent(requireActivity(), MyInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveConcernType(binding: FragmentMyInfoMainBinding) {
        binding.layoutConcernType.setOnClickListener {
            val intent = Intent(requireActivity(), ConcernTypeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveBookmark(binding: FragmentMyInfoMainBinding) {
        binding.layoutBookmark.setOnClickListener {
            val intent = Intent(requireActivity(), BookmarkActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveMyReview(binding: FragmentMyInfoMainBinding) {
        binding.layoutMyReview.setOnClickListener {
            val intent = Intent(requireActivity(), MyReviewActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun moveDeleteUser(binding: FragmentMyInfoMainBinding) {
        binding.layoutDelete.setOnClickListener {
            val intent = Intent(requireActivity(), DeleteUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logoutDialog(binding: FragmentMyInfoMainBinding) {
        binding.layoutLogout.setOnClickListener {
            val dialog = ConfirmDialog(
                this,
                "로그아웃",
                "해당 기기에서 로그아웃 됩니다.",
                "로그아웃",
                R.color.button_tertiary,
                R.color.white)
            dialog.isCancelable = false
            dialog.show(requireActivity().supportFragmentManager, "MyPageDialog")
        }
    }

    override fun onPosBtnClick() {
        logout()
    }

    private fun logout() {
        Snackbar.make(requireView(), "로그아웃", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        restoreStatusBarColor()
    }
}