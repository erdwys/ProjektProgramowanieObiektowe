

$(document).ready(function () {

    var table = $('#user_powiadomienia_Table').DataTable({
          dom: "Bfrtip",
        "processing": true,
        "serverSide": false,
          "deferRender": true,
            stateSave: true,
        "sAjaxSource": "/user_powiadomienia/Adminget",
        "sAjaxDataProp": "",
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Polish.json",
            select: {
                rows: {
                    _: "Zaznaczono %d wierszy",
                    0: "Kliknij w wiersz aby go zaznaczyć",
                    1: "Zaznaczono 1 wiersz"
                }
            }
        },
        dataType: 'json',
          columns: [
          {data: "idInformacja"},                  
            {data: "nrInformacji"},
            {data: "rokRozliczeniowy"},
            {data: "stanRozliczenia"},
            {data: "informacja"}        
        ],

        select: true,
        idSrc: "idInformacja",
                buttons: [

           {
                extend: 'collection',
                text: 'Export',
                buttons: [
                    'copy',
                    'excel',
                     {extend: 'pdfHtml5', orientation: 'landscape',
                pageSize: 'LEGAL', download: 'open'}
                ,
                    'print'
                ]
            }
                ]
    });
});


