function fetchData() {
    fetch("http://localhost:8080/solutions")
        .then(response => response.json())
        .then((response) => {
            document.getElementById("result").innerText = response.result;
            let text = "";
            for (let i = 0; i < response.values.length; i++) {
                text += response.values[i] + " ";
            }
            document.getElementById("container").innerHTML = text;
        })
        .catch(err => { throw err });
}
