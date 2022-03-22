package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.databinding.FragmentBlank2Binding
import com.example.myapplication.databinding.FragmentBlankBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var tvResult: TextView? = null
    private var rul: ImageView? = null
    private var random: Random? = null
    private var old_deegre = 0
    private var deegre = 0
    private var _binding : FragmentBlank2Binding? = null
    private val binding get() = _binding!!
    private val numbers = arrayOf(
        "32 RED", "15 BLACK", "19 RED", "4 BLACK",
        "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED",
        "6 BLACK", "27 RED", "13 BLACK", "36 RED", "11 BLACK", "30 RED",
        "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED", "33 BLACK",
        "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
        "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "0"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        // Inflate the layout for this fragment
        _binding = FragmentBlank2Binding.inflate(inflater, container, false)
        return binding.root
    }
    fun onClickStart(view: View?) {
        old_deegre = deegre % 360
        deegre = random!!.nextInt(3600) + 720
        val rotate = RotateAnimation(
            old_deegre.toFloat(), deegre.toFloat(), RotateAnimation.RELATIVE_TO_SELF,
            0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 3600
        rotate.fillAfter = true
        rotate.interpolator = DecelerateInterpolator()
        rotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                tvResult!!.text = ""
            }

            override fun onAnimationEnd(animation: Animation) {
                tvResult!!.text = getResult(360 - deegre % 360)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        rul!!.startAnimation(rotate)
    }

    private fun init() {
        tvResult = binding.tvResult
        rul = binding.rul
        random = Random()
    }

    private fun getResult(deegre: Int): String {
        var text = ""
        var factor_x = 1
        var factor_y = 3
        for (i in 0..36) {
            if (deegre >= BlankFragment2.FACTOR * factor_x && deegre < BlankFragment2.Companion.FACTOR * factor_y) {
                text = numbers[i]
            }
            factor_x += 2
            factor_y += 2
        }
        if (deegre >= BlankFragment2.Companion.FACTOR * 73 && deegre < 360 || deegre >= 0 && deegre < BlankFragment2.Companion.FACTOR * 1) text =
            numbers[numbers.size - 1]
        return text
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment2.
         */
        // TODO: Rename and change types and number of parameters
        private const val FACTOR = 4.86f
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}