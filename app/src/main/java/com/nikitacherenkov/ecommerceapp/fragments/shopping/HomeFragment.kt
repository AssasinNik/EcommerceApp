package com.nikitacherenkov.ecommerceapp.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nikitacherenkov.ecommerceapp.R
import com.nikitacherenkov.ecommerceapp.adapters.HomeViewPagerAdapter
import com.nikitacherenkov.ecommerceapp.databinding.FragmentHomeBinding
import com.nikitacherenkov.ecommerceapp.fragments.categories.AccessoryFragment
import com.nikitacherenkov.ecommerceapp.fragments.categories.ChairFragment
import com.nikitacherenkov.ecommerceapp.fragments.categories.CupboardFragment
import com.nikitacherenkov.ecommerceapp.fragments.categories.FurnitureFragment
import com.nikitacherenkov.ecommerceapp.fragments.categories.MainCategoryFragment
import com.nikitacherenkov.ecommerceapp.fragments.categories.TableFragment

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )

        binding.viewPager.isUserInputEnabled=false

        val viewPager2Adapter = HomeViewPagerAdapter(categoriesFragments, childFragmentManager, lifecycle)

        binding.viewPager.adapter=viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            when(position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }

}