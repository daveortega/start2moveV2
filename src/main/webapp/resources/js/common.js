/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ProcessLanguage() {
    document.getElementById("lngMenuForm:translateMenu").click();
    document.getElementById("lngForm:translate").click();
}

function ProcessBarChart(JSObject, Label, Labels, Values) {
    var LabelsArray = Labels.split(",");
    var ValuesArray = Values.split(",");
    var myChart = new Chart(document.getElementById(JSObject), {
        type: 'bar',
        data: {
            labels: LabelsArray,
            datasets: [{
                    label: Label,
                    data: ValuesArray,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            }
        }
    });
}

function Process2BarChart(JSObject, Label1, Label2, Labels, Values1, Values2) {
    var LabelsArray = Labels.split(",");
    var Values1Array = Values1.split(",");
    var Values2Array = Values2.split(",");
    var myChart = new Chart(document.getElementById(JSObject), {
        type: 'bar',
        data: {
            labels: LabelsArray,
            datasets: [{
                    label: Label1,
                    data: Values1Array,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                },
                {
                    label: Label2,
                    data: Values2Array,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            }
        }
    });
}

function ProcessPieChart(JSObject, Label, Labels, Values) {
    var LabelsArray = Labels.split(",");
    var ValuesArray = Values.split(",");
    var myChart = new Chart(document.getElementById(JSObject), {
        type: 'doughnut',
        data: {
            labels: LabelsArray,
            datasets: [{
                    label: Label,
                    data: ValuesArray,
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)',
                        'rgb(0, 189, 184)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            }
        }
    });
}