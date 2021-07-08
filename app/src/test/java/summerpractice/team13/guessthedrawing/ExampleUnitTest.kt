package summerpractice.team13.guessthedrawing

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.OngoingStubbing
import summerpractice.team13.guessthedrawing.mvp.presenters.AppPreferences
import summerpractice.team13.guessthedrawing.mvp.presenters.change_difficulty_presenter.ChangeDifficultyPresenter
import summerpractice.team13.guessthedrawing.mvp.views.change_time_view.IChangeDifficultyView

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var view: IChangeDifficultyView

    @Spy
    private lateinit var presenter: ChangeDifficultyPresenter


    @Before
    fun addition_isCorrect() {
        presenter = ChangeDifficultyPresenter(view)

        
    }

    @Test
    fun test1(){

        Mockito.`when`(presenter.switchDifficulty(1))



    }
}

