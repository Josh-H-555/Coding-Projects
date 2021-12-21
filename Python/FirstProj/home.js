function buildQuiz()
{
    const output = [];

    Questions.forEach(
        (currentQuestion, questionNumber) =>
    {
        const answers = [];

        for(letter in currentQuestion.answers)
        {
            answers.push(
                `<label>
                    <input type="radio" name="question${questionNumber}" value="${letter}">
                    ${letter} :
                    ${currentQuestion.answers[letter]}

                </label>`
                );
        }

        output.push(
            `<div class="question"> ${currentQuestion.question} </div>
            <div class="answers"> ${answers.join('')} </div>`
            );
    }
    );


    quizContainer.innerHTML = output.join('');
}

function showResults()
{
    const answerContainers = quizContainer.querySelectorAll('.answers');

    let numCorrect = 0;

    Questions.forEach( (currentQuestion, questionNumber) =>
    {
        const answerContainer = answerContainers[questionNumber];
        const selector = `input[name=question${questionNumber}]:checked`;
        const userAnswer = (answerContainer.querySelector(selector) || {}).value;

        if (userAnswer === currentQuestion.correct)
        {
            ++numCorrect;

            answerContainers[questionNumber].style.color = 'lightgreen';
        }

        else
        {
            answerContainers[questionNumber].style.color = 'red';
        }

    });

    resultsContainer.innerHTML = `${numCorrect} out of ${Questions.length}`;
}


const quizContainer = document.getElementById('quiz');
const resultsContainer = document.getElementById('results');
const submitButton = document.getElementById('submit');

const Questions = [

    {
        question: "Where does Dark Souls 1 Take place?",
        answers:
        {
            a: "Lordran",
            b: "Drangleic",
            c: "Lothric"
        },
        correct: "a"
    },
    {
        question: "In what game is the player character the 'Champion of Ash'?",
        answers:
        {
            a: "Dark Souls 1",
            b: "Dark Souls 2",
            c: "Dark Souls 3"

        },
        correct: "c"
    },

    {
        question: "What is the main hub of Dark Souls 2?",
        answers:
        {
            a: "Firelink Shrine",
            b: "Majula",
            c: "Hunter's Dream"
        },
        correct: "b"
    }


    ];




buildQuiz();

submitButton.addEventListener('click', showResults);



