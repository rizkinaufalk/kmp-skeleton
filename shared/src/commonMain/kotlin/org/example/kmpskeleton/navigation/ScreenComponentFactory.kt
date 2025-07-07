package org.example.kmpskeleton.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import org.example.kmpskeleton.RickAppDB
import org.example.kmpskeleton.data.repositories.CharacterRepoImpl
import org.example.kmpskeleton.domain.usecase.DeleteAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.example.kmpskeleton.domain.usecase.InsertFavoriteUseCase
import org.example.kmpskeleton.domain.usecase.IsFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.RemoveFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.ToggleFavCharacterUseCase
import org.example.kmpskeleton.features.character.DefaultCharListComponent
import org.example.kmpskeleton.features.character.details.DefaultCharDetailComponent
import org.example.kmpskeleton.features.character.favourites.DefaultFavoritesComponent
import org.example.kmpskeleton.features.settings.DefaultSettingsComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface ScreenComponentFactory {
    fun create(
        config: DefaultRootComponent.Config,
        componentContext: ComponentContext,
        onFinished: () -> Unit,
        nav: StackNavigation<DefaultRootComponent.Config>
    ): RootComponent.Child
}

class ScreenComponentFactoryImpl : ScreenComponentFactory, KoinComponent {

    override fun create(
        config: DefaultRootComponent.Config,
        componentContext: ComponentContext,
        onFinished: () -> Unit,
        nav: StackNavigation<DefaultRootComponent.Config>
    ): RootComponent.Child {

        return when (config) {
            DefaultRootComponent.Config.CharList -> {
                val getCharacterUseCase = get<GetCharacterUseCase>()

                RootComponent.Child.CharList(
                    DefaultCharListComponent(
                        componentContext = componentContext,
                        getCharacterUseCase = getCharacterUseCase,
                        charClicked = {char ->  nav.push(DefaultRootComponent.Config.CharDetail(char)) }
                    )
                )
            }

            is DefaultRootComponent.Config.CharDetail -> {
                val toggleFavCharacterUseCase = get<ToggleFavCharacterUseCase>()
                val isFavCharacterUseCase = get<IsFavCharacterUseCase>()

                RootComponent.Child.CharDetail(
                    DefaultCharDetailComponent(
                        componentContext = componentContext,
                        character = config.char,
                        toggleFavCharacterUseCase = toggleFavCharacterUseCase,
                        isFavCharacterUseCase = isFavCharacterUseCase,
                        onFinished = onFinished
                    )
                )
            }

            DefaultRootComponent.Config.Favorites -> {
                val getAllFavCharacterUseCase = get<GetAllFavCharacterUseCase>()
                RootComponent.Child.Favorites(
                   DefaultFavoritesComponent(
                       componentContext = componentContext,
                       getAllFavCharacterUseCase = getAllFavCharacterUseCase,
                       charClicked = {char ->  nav.push(DefaultRootComponent.Config.CharDetail(char)) }
                   )
                )

            }
            DefaultRootComponent.Config.Settings -> {
                val deleteAllFavCharacterUseCase = get<DeleteAllFavCharacterUseCase>()
                RootComponent.Child.Settings(
                    DefaultSettingsComponent(
                        componentContext = componentContext,
                        deleteAllFavCharacterUseCase = deleteAllFavCharacterUseCase
                    )
                )
            }
        }
    }
}