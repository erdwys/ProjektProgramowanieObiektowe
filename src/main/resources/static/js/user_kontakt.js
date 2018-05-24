
function send_Form() {
var name = document.getElementById("name").value;
var basicInfo = JSON.stringify(
                    {
                        informacja : name
                    
                    });
                    
                     $.ajax({
                    url: "/send_Form",
                    type: 'POST',
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    data: basicInfo,
                    success: function(data) {



                    }
                });


}