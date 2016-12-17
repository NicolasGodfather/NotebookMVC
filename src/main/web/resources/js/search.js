function search() {
    $.ajax({
        type: "GET",
        url: "/all/person/search/" + $('#searchCriteria').val(),
        success: function (result) {
            $("#tableCurrency").html(result);
        }
    })
}