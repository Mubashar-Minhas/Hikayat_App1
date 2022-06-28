package com.nebulark.hikayatapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ShareCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.nebulark.hikayatapp.models.HikayatDataModel


@Suppress("DEPRECATION")
class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var visibilityCheck: Boolean = false
    private lateinit var view1: View
    private lateinit var mBottomSheetLayout: LinearLayout
    private lateinit var mConstraintLayout: ConstraintLayout
    private lateinit var mLoadingLayout: LinearLayout
    private lateinit var mainLayout: LinearLayout
    private lateinit var mExpandedLayout: ConstraintLayout
    private lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var header_Arrow_Image: ImageView
    private lateinit var mTextViewBookTag: MaterialTextView
    private lateinit var mTextViewCategoryTag: MaterialTextView
    private lateinit var mTextViewStory: MaterialTextView
    private lateinit var mTextViewLabel: MaterialTextView
    private lateinit var mTextViewLabel1: MaterialTextView
    private lateinit var mTextViewLabel2: MaterialTextView
    private lateinit var shareTextButton: ImageButton
    private lateinit var shuffleButton: ImageButton
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
   // private  lateinit var database: DatabaseReference
    val db = Firebase.firestore
    private lateinit var hikayatModel: HikayatDataModel
    private var storiesArrayList: ArrayList<HikayatDataModel>? = null


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
        mTextViewStory= view1.findViewById<MaterialTextView>(R.id.hikayatStory)
        mTextViewLabel = view1.findViewById<MaterialTextView>(R.id.textViewLabel)
        mTextViewLabel1 = view1.findViewById<MaterialTextView>(R.id.textViewLabel1)
        mTextViewLabel2= view1.findViewById<MaterialTextView>(R.id.textViewLabel2)
        shareTextButton = view1.findViewById(R.id.shareButton)
        shuffleButton = view1.findViewById(R.id.shuffleButton)
        mLoadingLayout = view1.findViewById(R.id.linearLayoutLoadingView)
        mainLayout = view1.findViewById(R.id.linearLayout)
        mConstraintLayout = view1.findViewById(R.id.constraint_main_layout)
        drawer = view1.findViewById(R.id.drawer_layout)

        navigationView = view1.findViewById(R.id.nav_view)


        mConstraintLayout.setOnClickListener { openOrCloseDrawer() }


        // navigationView.setNavigationItemSelectedListener(this)

        //listener for shuffle btn

        shuffleButton.setOnClickListener(View.OnClickListener {
            rotateAndLoad()

            //stopLoading()


        })


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
        getDataFromFirebase();

    }

    private fun getDataFromFirebase() {
        db.collection("stories")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.d("msg", "${document.id} => ${document.data["body"]}")

                   // hikayatModel=result.toObject<HikayatDataModel>()

                    //val types: List<HikayatDataModel> = result.toObjects<HikayatDataModel>()


                   // storiesArrayList?.add(hikayatModel);
                    mTextViewStory.text = document.getString("body")
                    mTextViewBookTag.text = document.getString("book")
                    mTextViewCategoryTag.text = document.getString("category")




                   //                    mTextViewStory.setText()
                }

            }
            .addOnFailureListener { exception ->
                Log.w("msg", "Error getting documents.", exception)
                print("msg"+ exception.toString())
            }


    }

    private fun openOrCloseDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.END)
        } else {
            drawer.openDrawer(GravityCompat.START)
        }
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun stopLoading() {
        mLoadingLayout.visibility = View.INVISIBLE
        mLoadingLayout.visibility = View.GONE
        mainLayout.visibility = View.VISIBLE
    }

    private fun rotateAndLoad() {
        Log.d("check", "aya1 hy")
        println("coming;;;;;;;;;;;;;;;")
//        mLoadingLayout.visibility = View.VISIBLE
//        mainLayout.visibility = View.INVISIBLE


        val clk_rotate = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_clockwise

        )


        // assigning that animation to
        // the image and start animation
        shuffleButton.startAnimation(clk_rotate)


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


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_books -> {
                findNavController().navigate(R.id.action_homeFragment2_to_booksFragment)

            }
            R.id.nav_category -> {
                findNavController().navigate(R.id.action_homeFragment2_to_categoryFragment)
            }
        }


        //val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}