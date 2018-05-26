
$(document).ready(function () {



    var table = $('#user_kontakt_Table').DataTable({
          dom: "Bfrtip",
         destroy: true,
        "processing": true,
        "serverSide": false,
          "deferRender": true,
            stateSave: true,
        "sAjaxSource": "/user_powiadomienia/UsergetId",
        
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

          columns: [
              {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
            {data: "idInformacja"},
            {data: "dzialki",
                render: function (data, type, full) {
                    if (data.nrDzialki !== undefined) {
                        return data.nrDzialki;
                    } else {
                        return data;
                    }

                }},         
            {data: "nrInformacji"},
            {data: "rokRozliczeniowy"},
            {data: "informacja"}
        ],
order: [ 1, 'asc' ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
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





