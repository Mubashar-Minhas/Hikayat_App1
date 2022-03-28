package com.nebulark.hikayatapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.textview.MaterialTextView


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var view1: View
    private lateinit var mBottomSheetLayout: LinearLayout
    private lateinit var mExpandedLayout: ConstraintLayout
    private lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var header_Arrow_Image: ImageView
    private lateinit var mTextViewBookTag: MaterialTextView
    private lateinit var mTextViewCategoryTag: MaterialTextView
    private lateinit var shareTextButton:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view1 = view
        init()


        header_Arrow_Image.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                mExpandedLayout.visibility = View.VISIBLE
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                mExpandedLayout.visibility = View.INVISIBLE
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

                mExpandedLayout.visibility = View.GONE

            }
        }
        sheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                header_Arrow_Image.rotation = slideOffset * 180
            }
        })
    }

    private fun init() {
        mBottomSheetLayout = view1.findViewById<LinearLayout>(R.id.bottom_sheet_layout)
        mExpandedLayout = view1.findViewById<ConstraintLayout>(R.id.expanded_layout)
        sheetBehavior = BottomSheetBehavior.from<LinearLayout>(mBottomSheetLayout)

        header_Arrow_Image = view1.findViewById<ImageView>(R.id.bottom_sheet_arrow)
        mTextViewBookTag = view1.findViewById<MaterialTextView>(R.id.textViewBookName)
        mTextViewCategoryTag = view1.findViewById<MaterialTextView>(R.id.textViewCategory)
        shareTextButton=view1.findViewById(R.id.shareButton);

        shareTextButton.setOnClickListener(View.OnClickListener {

            // COMPLETED (5) Specify a String you'd like to share
            /* Create the String that you want to share */
            val textThatYouWantToShare =
                "Sharing the coolest thing I've learned so far. You should " +
                        "check out Udacity and Google's Android Nanodegree!"
            shareText(textThatYouWantToShare)
        })

        mTextViewBookTag.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_booksFragment)
        })

        mTextViewCategoryTag.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_categoryFragment)
        })

    }

    private fun shareText(textToShare: String) {


        val mimeType = "text/plain"


        val title = "Learning How to Share"


        ShareCompat.IntentBuilder /* The from method specifies the Context from which this share is coming from */
            .from(requireContext() as Activity)
            .setType(mimeType)
            .setChooserTitle(title)
            .setText(textToShare)
            .startChooser()

    }





}