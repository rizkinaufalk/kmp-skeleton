package org.example.kmpskeleton.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.example.kmpskeleton.R
import org.example.kmpskeleton.ui.components.CharListItem

@Preview(showBackground = true)
@Composable
fun CharListItemPreview(){
    MaterialTheme {
        CharListItem("https://rickandmortyapi.com/api/character/avatar/1.jpeg", "Ini Error")
    }
}