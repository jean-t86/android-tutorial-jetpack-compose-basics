package au.com.broscience.jetpackcomposebasicstutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import au.com.broscience.jetpackcomposebasicstutorial.ui.theme.JetpackComposeBasicsTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }
}

@Composable
fun NewsStory() {
    Column {
        Text(text = "A day in Shark Fin Cove")
        Text(text = "Davenport, California")
        Text(text = "December 2018")
    }
}

@Preview
@Composable
fun defaultPreview() {
    NewsStory()
}