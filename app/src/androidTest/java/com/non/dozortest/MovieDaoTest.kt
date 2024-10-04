import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.non.dozortest.dao.MovieDao
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.database.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setUp() {
        // Create an in-memory database for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        movieDao = database.movieDAO()
    }

    @After
    fun tearDown() {
        // Close the database after the test is complete
        database.close()
    }

    @Test
    fun insertAndRetrieveMovie() = runBlocking {
        // Create a movie object
        val movie = Movie(
            id = 1,
            originalTitle = "Inception",
            posterPath = "PosterPath",
            description = "Description",
            releaseDate = "releaseDate",
            voteAverage = "voteAverage",
            voteCount = "voteCount"
        )

        // Insert the movie into the database
        movieDao.insertMovie(movie)

        // Retrieve the movie from the database by its ID
        val retrievedMovie = movieDao.getMovieById(1).first()

        // Verify that the retrieved movie matches the inserted one
        assertThat(retrievedMovie, equalTo(movie))
    }
}