
let studentName = "";
let time = 600;
let timerInterval;
let submitted = false;

function login() {

    let name = document.getElementById("name").value;
    let password = document.getElementById("password").value;

    if (name === "" || password === "") {
        document.getElementById("error").innerHTML =
            "Please enter name and password";
        return;
    }

    studentName = name;

    document.getElementById("login").style.display = "none";
    document.getElementById("exam").style.display = "block";

    document.getElementById("welcome").innerHTML =
        "Welcome " + studentName;

    startTimer();
}

function startTimer() {

    timerInterval = setInterval(function () {

        let minutes = Math.floor(time / 60);
        let seconds = time % 60;

        document.getElementById("timer").innerHTML =
            minutes + ":" + (seconds < 10 ? "0" : "") + seconds;

        time--;

        if (time < 0) {
            submitExam();
        }

    }, 1000);
}

function submitExam() {

    if (submitted) return;

    submitted = true;
    clearInterval(timerInterval);

    let answers = [1, 2, 0, 2, 1];
    let score = 0;

    for (let i = 1; i <= 5; i++) {

        let selected = document.querySelector(
            'input[name="q' + i + '"]:checked'
        );

        if (selected && Number(selected.value) === answers[i - 1]) {
            score++;
        }
    }

    let percentage = (score / 5) * 100;

    document.getElementById("exam").style.display = "none";
    document.getElementById("result").style.display = "block";

    document.getElementById("studentResult").innerHTML =
        "Student Name: " + studentName;

    document.getElementById("score").innerHTML =
        "Your Score: " + score + " / 5";

    document.getElementById("percentage").innerHTML =
        "Percentage: " + percentage + "%";
}
