package org.example.kmpskeleton.navigation


import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.features.character.CharListComponent
import org.example.kmpskeleton.features.character.details.CharDetailComponent
import org.example.kmpskeleton.features.character.favourites.FavoritesComponent
import org.example.kmpskeleton.features.settings.SettingsComponent
import org.example.kmpskeleton.navigation.DefaultRootComponent.Config

interface RootComponent {
    val routerState: Value<ChildStack<*, Child>>

    fun onBack()

    sealed interface Child {
        class CharList(val component: CharListComponent) : Child
        class CharDetail(val component: CharDetailComponent) : Child
        class Favorites(val component: FavoritesComponent) : Child
        class Settings(val component: SettingsComponent) : Child
    }
}