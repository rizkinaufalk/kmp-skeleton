package org.example.kmpskeleton.navigation


import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.example.kmpskeleton.features.character.CharListComponent
import org.example.kmpskeleton.features.character.details.CharDetailComponent

sealed class Screen{
    object CharacterList: Screen()
    data class CharacterDetail(val id: String) : Screen()
}

interface RootComponent {
    val routerState: Value<ChildStack<*, Child>>

    fun onBack()

    sealed interface Child {
        class CharList(val component: CharListComponent) : Child
        class CharDetail(val component: CharDetailComponent) : Child
    }
}