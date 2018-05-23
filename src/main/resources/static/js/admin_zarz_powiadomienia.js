var editor;

$(document).ready(function () {

  $.extend( $.fn.dataTable.Editor.display.envelope.conf, {
        attach: 'head'
    } );



    editor = new $.fn.dataTable.Editor({
     display: 'envelope',
         ajax: {
                        create: {
           		
                url: '/admin_zarz_powiadomienia/add',
                   type: 'POST',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: function (d) {
                    var obj;
                    for (var key in d.data) {
                        obj = d.data[key];
                        break;
                    }
                    obj["idInformacja"] = 1;
                        obj["nadawca"] = "ADMIN";
                    return JSON.stringify(obj);
                },

                success: function (data) {
                      editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                },
                error: function (e) {
                    editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                }



                        },
                        edit: {
                                type: 'PUT',
                             url: "/admin_zarz_powiadomienia/update",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',

                data: function (d) {
                    var obj;
                    for (var key in d.data) {
                        obj = d.data[key];
                        break;
                    }
                    return JSON.stringify(obj);
                },
                success: function (data) {
                    editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                },
                error: function (e) {
                   editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                }

                        },
                        remove: {

                                type: 'DELETE',
                               url: "/admin_zarz_powiadomienia/delete",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',

                data: function (d) {
                    var obj;
                    for (var key in d.data) {
                        obj = d.data[key];
                        break;
                    }
                    JSON.stringify(obj);
                    return {"id": obj["idInformacja"]};
                },
                success: function (data) {
                 editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                },
                error: function (e) {
                      editor.close();
                  $.fn.dataTable.ext.errMode = 'none';
                    location.reload();
                }            

            }

                },
        "table": "#admin_zarz_powiadomienia_Table",
        idSrc: "idInformacja",
        "fields": [{
                "label": "ID Informacji",
                "name": "idInformacja"
            }, {
                "label": "Nadawca",             
                "name":  "nadawca"
            }, {
                "label": "Nr dzialki",             
                "name":"dzialki.nrDzialki"
            }, {
                "label": "Nr informacji",
                "name": "nrInformacji"
            }, {
                "label": "Rok rozliczeniowy",
                "name": "rokRozliczeniowy"             
            }, {
                "label": "Stan rozliczenia",
                "name": "stanRozliczenia"
            }, {
                "label": "Informacja",
                "name": "informacja"
            }
        ],

        i18n: {
            create: {
                button: "Nowy",
                title: "Tworzenie nowego powiadomienia",
                submit: "Stwórz"
            },
            edit: {
                button: "Zmodyfikuj",
                title: "Modyfikacja wybranego powiadomienia",
                submit: "Modyfikuj"
            },
            remove: {
                button: "Usuń",
                title: "Usuwanie wybranego powiadomienia",
                submit: "Usuń",
                confirm: {
                    1: "Czy na pewno chcesz usunąć?"
                }
            },
            error: {
                system: "Wystąpił błąd, skontaktuj się z administratorem systemu"
            }
        }
    });

$('#admin_zarz_powiadomienia_Table').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this, {
            onBlur: 'submit',
              submit: 'allIfChanged'
        } );
    } );

    editor.field('idInformacja')
            .disable();
       editor.field('nadawca')
            .disable();

    editor.on('preSubmit', function (e, o, action) {

        if (action !== 'remove') {
            var nrDzialki = this.field('dzialki.nrDzialki');
          
            var nrInformacji = this.field('nrInformacji');
   
            var informacja = this.field('informacja');


            if (!nrDzialki.isMultiValue()) {
                if (!nrDzialki.val()) {
                    nrDzialki.error('Proszę podać numer działki');
                } else {
                    if (!isNumber(nrDzialki.val())) {
                        nrDzialki.error('Proszę podać poprawny numer działki');
                    }

                }
            }

            if (!nrInformacji.isMultiValue()) {
                if (!nrInformacji.val()) {
                    nrInformacji.error('Proszę podać numer informacji');
                } else {
                    if (!isNumber(nrInformacji.val())) {
                        nrInformacji.error('Proszę podać poprawny numer informacji');
                    }

                }
            }
           
            if (!informacja.isMultiValue()) {
                if (!informacja.val()) {
                    informacja.error('Proszę wprowadzić informacje');
                } 

                }
            






            if (this.inError()) {
                return false;
            }
        }
    });

    function isNumber(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }
    


    var table = $('#admin_zarz_powiadomienia_Table').DataTable({
          dom: "Bfrtip",
        "processing": true,
        "serverSide": false,
          "deferRender": true,
            stateSave: true,
        "sAjaxSource": "/admin_zarz_powiadomienia/get",
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
                {data: "nadawca"},
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
            {data: "stanRozliczenia"},
            {data: "informacja"}
        ],
order: [ 1, 'asc' ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        idSrc: "idInformacja",
                buttons: [
                {extend: "create", editor: editor,
                formButtons: ['Stwórz', {text: 'Powrót', action: function () {
                            this.close();
                        }}]},
                        {extend: "edit",   editor: editor,
                formButtons: ['Modyfikuj', {text: 'Powrót', action: function () {
                            this.close();
                        }}]},
                        {extend: "remove", editor: editor,
                formButtons: ['Usuń', {text: 'Powrót', action: function () {
                            this.close();
                        }}]},
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

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
        return JSON.stringify({
                "idInformacja": $('idInformacja').val(),
                "dzialki.nrDzialki": $('#dzialki.nrDzialki').val(),
                        "nadawca": $('#nadawca').val(),
                "nrInformacji": $('#nrInformacji').val(),
          "rokRozliczeniowy": $('#rokRozliczeniowy').val(),
          "stanRozliczenia": $('#stanRozliczenia').val(),
                "informacja": $('#informacja').val()
                                            
            });
}


