package org.example.kmpskeleton.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.example.kmpskeleton.ui.navigation.RickAppDestinations

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigateToCharList: () -> Unit,
    navigateToCharDetail: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier
    ) {
        NavigationDrawerItem(
            label = { Text("Character List") },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == RickAppDestinations.CHAR_LIST_ROUTE,
            onClick = {

                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
    }
}

@Preview
@Composable
fun previewDrawer(){
    MaterialTheme {
        AppDrawer(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            currentRoute = RickAppDestinations.CHAR_LIST_ROUTE,
            navigateToCharList = {},
            navigateToCharDetail = {},
            closeDrawer = {}
        )
    }
}