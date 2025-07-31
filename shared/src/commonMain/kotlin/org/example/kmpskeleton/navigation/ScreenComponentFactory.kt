package org.example.kmpskeleton.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import org.example.kmpskeleton.domain.usecase.CharacterUseCases
import org.example.kmpskeleton.domain.usecase.DeleteAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterPageUseCase
import org.example.kmpskeleton.domain.usecase.IsCharacterExistUseCase
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

        val characterUseCases = get<CharacterUseCases>()

        return when (config) {
            DefaultRootComponent.Config.CharList -> {
                RootComponent.Child.CharList(
                    DefaultCharListComponent(
                        componentContext = componentContext,
                        characterUseCases = characterUseCases,
                        charClicked = {charId ->  nav.push(DefaultRootComponent.Config.CharDetail(charId)) }
                    )
                )
            }

            is DefaultRootComponent.Config.CharDetail -> {
                RootComponent.Child.CharDetail(
                    DefaultCharDetailComponent(
                        componentContext = componentContext,
                        characterId = config.characterrId,
                        characterUseCases = characterUseCases,
                        onFinished = onFinished
                    )
                )
            }

            DefaultRootComponent.Config.Favorites -> {
                RootComponent.Child.Favorites(
                   DefaultFavoritesComponent(
                       componentContext = componentContext,
                       characterUseCases = characterUseCases,
                       charClicked = {char ->  nav.push(DefaultRootComponent.Config.CharDetail(char)) }
                   )
                )

            }
            DefaultRootComponent.Config.Settings -> {
                RootComponent.Child.Settings(
                    DefaultSettingsComponent(
                        componentContext = componentContext,
                        characterUseCases = characterUseCases
                    )
                )
            }
        }
    }
}