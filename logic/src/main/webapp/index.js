const API_URL = "http:localhost:8080/";

const data = [
    {id: 0, name: "Head"},
    {id: 1, name: "Slaves"}
];
function fetchDepartments() {
    // $.ajax({
    //     url: API_URL + "departments",
    //     dataType: "json"
    // }).then(data => {
    //
    // });
    // $("body").append("Hello world");



    for (var i = 0; i < data.length; i++) {
        const bound = listEmployee.bind({id: data[i].id});
        $(".department-container").append(`
            <tr>
                <td>${data[i].id}</td>            
                <td>${data[i].name}</td>                
                <td>
                    <button id=${"deps" + data[i].id} class="btn btn-primary">List employee</button>                
                </td>            
            </tr>
        `);
        $("#deps" + data[i].id).click(() => bound());
    }

}

const listEmployee = function() {
    console.log("Performing request for id = " + this.id);
};

fetchDepartments();