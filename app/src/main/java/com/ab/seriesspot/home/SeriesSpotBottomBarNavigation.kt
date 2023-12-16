package com.ab.seriesspot.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ab.seriesspot.R


sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: Int, val selectedIcon : Int) {
    object Home : BottomNavigationScreens("Home", R.string.home, R.drawable.ic_home,R.drawable.ic_selected_home )
    object Search : BottomNavigationScreens("Search", R.string.search, R.drawable.ic_search,R.drawable.ic_selected_search)
    object NewAndHot : BottomNavigationScreens("New & Hot", R.string.new_and_hot, R.drawable.ic_news_and_hot, R.drawable.ic_selected_news_and_hot)
    object Downloads : BottomNavigationScreens("Downloads", R.string.downloads, R.drawable.ic_downloads, R.drawable.ic_selected_downloads)
    object MySpace : BottomNavigationScreens("My Space", R.string.my_space, R.drawable.ic_my_space,R.drawable.ic_selected_my_space)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen()
{

    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.NewAndHot,
        BottomNavigationScreens.Downloads,
        BottomNavigationScreens.MySpace
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            SeriesSpotBottomNavigation(navController, bottomNavigationItems)
        },
    ) {paddingValues ->
        MainScreenNavigationConfigurations(navController,paddingValues)
    }
}


@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,paddingValues : PaddingValues
)
{
    NavHost(
        navController,
        modifier = Modifier.padding(paddingValues = paddingValues),
        startDestination = BottomNavigationScreens.Home.route
    ) {
        composable(BottomNavigationScreens.Home.route) {

        }
        composable(BottomNavigationScreens.Search.route) {
        }
        composable(BottomNavigationScreens.NewAndHot.route) {
        }
        composable(BottomNavigationScreens.Downloads.route) {
        }
        composable(BottomNavigationScreens.MySpace.route){

        }
    }
}


@Composable
private fun SeriesSpotBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
)
{
    NavigationBar {
        var navigationSelectedItem by remember { mutableStateOf(0) }

        items.forEachIndexed { index,screen ->
            val isSelected = navigationSelectedItem == index
            NavigationBarItem(
                icon = {
                    if(isSelected)
                        Image(painterResource(id = screen.selectedIcon), contentDescription = "destination" )
                    else
                        Image(painterResource(id = screen.icon), contentDescription = "destination" )
                       },
                label = {
                    Text(
                        stringResource(id = screen.resourceId),
                        color = if (isSelected) Color.White else Color.Gray
                    )
                        },
                selected = isSelected,
                onClick = {
                    if (navigationSelectedItem != index) {
                        navigationSelectedItem = index
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }


                        }
                    }
                }
            )
        }
    }
}