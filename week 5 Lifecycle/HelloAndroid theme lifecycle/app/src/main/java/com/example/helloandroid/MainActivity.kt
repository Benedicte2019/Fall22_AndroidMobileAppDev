package com.example.helloandroid

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloandroid.ui.theme.CMUTheme
import com.example.helloandroid.ui.theme.CMUred
import com.example.helloandroid.ui.theme.CMUreddark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("oncreate","restart")
        setContent {
            CMUTheme() {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    var name by rememberSaveable { mutableStateOf("") }
    var textFieldName by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState(), enabled = true)
    )
    {
        NameTextField(name = textFieldName, changed = { textFieldName = it })
        SayHi({ name = textFieldName })
        Image(
            painter = painterResource(id = R.drawable.scottydog),
            contentDescription = stringResource(id = R.string.scotty),
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)
                .size(190.dp)
                .clip(CircleShape),

        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(CMUreddark)
        ) {
            MessageText(newName = name)
        }
    }
}

@Composable
fun SayHi(clicked: () -> Unit){
    Button(onClick= clicked) {
        Text(
            stringResource(id = R.string.buttonHello),
        )
    }
}

@Composable
fun NameTextField(name: String, changed: (String) ->Unit){
    TextField(
        value = name,
        label = {Text(stringResource(id = R.string.enterName))},
        onValueChange = changed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    )
}

@Composable
fun MessageText(newName: String){
    if(newName.isNotEmpty()) {
        Text(
            stringResource(R.string.greeting) + " " + newName,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CMUTheme(darkTheme = false) {
        Greeting()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkDefaultPreview() {
    CMUTheme(darkTheme = true) {
        Greeting()
    }
}