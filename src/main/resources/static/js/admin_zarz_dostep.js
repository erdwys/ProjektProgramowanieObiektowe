var editor;

$(document).ready(function () {

  $.extend( $.fn.dataTable.Editor.display.envelope.conf, {
        attach: 'head'
    } );

  var optionsA = [];
$.getJSON("/admin_zarz_user/getID", {
        term: "-1"
    },
    function(data) {
        var option = {};
        $.each(data, function(i, e) {
            option.label = e;
            option.value = e;
            optionsA.push(option);
            option = {};
        });
    }
).done(function() {
  
  
  
    editor.field('dzialkowicz.nrDzialkowicza').update(optionsA);
});
     


    editor = new $.fn.dataTable.Editor({
     display: 'envelope',
         ajax: {
                        create: {
           		
                url: '/admin_zarz_dostep/add',
                   type: 'POST',
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
                    table.clear().draw();
                    table.ajax.reload();
                },
                error: function (e) {
                    alert("ERROR: ", e);
                }



                        },
                        edit: {
                                type: 'PUT',
                             url: "/admin_zarz_dostep/update",
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
                    table.clear().draw();
                    table.ajax.reload();
                },
                error: function (e) {
               editor.close();
                    table.clear().draw();
                    table.ajax.reload();
                }

                        },
                        remove: {

                                type: 'DELETE',
                               url: "/admin_zarz_dostep/delete",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',

                data: function (d) {
                    var obj;
                    for (var key in d.data) {
                        obj = d.data[key];
                        break;
                    }
                    JSON.stringify(obj);
                    return {"id": obj["dzialkowicz.nrDzialkowicza"]};
                },
                success: function (data) {
                           editor.close();
                    table.clear().draw();
                    table.ajax.reload();
                },
                error: function (e) {
                           editor.close();
                    table.clear().draw();
                    table.ajax.reload();
                }            

            }
                },
        "table": "#admin_zarz_dostep_Table",
        idSrc: "dzialkowicz.nrDzialkowicza",
        "fields": [{
                "label": "Nr dzialkowicza",
                "name": "dzialkowicz.nrDzialkowicza",
                 type:  "select"
            }, {
                "label": "login",
                "name": "login"
            }, {
                "label": "password",
                "name": "password"
            }, {
                "label": "enabled",
                "name": "enabled"
            }, {
                "label": "role",
                "name": "role"
            }
        ],

        i18n: {
            create: {
                button: "Nowy",
                title: "Tworzenie nowego dostepu",
                submit: "Stwórz"
            },
            edit: {
                button: "Zmodyfikuj",
                title: "Modyfikacja wybranego dostep",
                submit: "Modyfikuj"
            },
            remove: {
                button: "Usuń",
                title: "Usuwanie wybranego dostep",
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

  $('#admin_zarz_dostep_Table').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this, {
            onBlur: 'submit',
              submit: 'allIfChanged'
        } );
    } );

    editor.on('preSubmit', function (e, o, action) {

        if (action !== 'remove') {
            var login = this.field('login');
            var password = this.field('password');
            var enabled = this.field('enabled');
            var role = this.field('role');


            if (!login.isMultiValue()) {
                if (!login.val()) {
                    login.error('Proszę podać login');
                } else {
                    if (login.val().length < 4) {
                        login.error('login nie może być krótsze niż 4 znaki');
                    }
                }
            }

            if (!password.isMultiValue()) {
                if (!password.val()) {
                    password.error('Proszę podać hasło');
                } else {
                    if (!validatePassword(password.val())) {
                        password.error('Hasło musi zawierać: osiem znaków, w tym: 1 wielka litera, 1 mała litera i 1 cyfra');
                    }
                }

            }

            if (!enabled.isMultiValue()) {
                if (!enabled.val()) {
                    enabled.error('Proszę wprowadzić wartość true lub false');
                } else {
                    if (String(enabled.val()) !== 'true' && String(enabled.val()) !== 'false') {
                        enabled.error('Proszę wprowadzić wartość true lub false');
                    }
                }
            }
            if (!role.isMultiValue()) {
                if (!role.val()) {
                    role.error('Proszę wprowadzić wartość ROLE_USER lub ROLE_ADMIN');
                } else {
                    if ((String(role.val()) !== 'ROLE_USER') && (String(role.val()) !== 'ROLE_ADMIN')) {
                        role.error('Proszę wprowadzić wartość ROLE_USER lub ROLE_ADMIN');
                    }
                }
            }


            if (this.inError()) {
                return false;
            }
        }
    });


    function validatePassword(password) {
        var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
        return re.test(String(password));
    }



    var table = $('#admin_zarz_dostep_Table').DataTable({
          dom: "Bfrtip",
        "processing": true,
        "serverSide": false,
          "deferRender": true,
            stateSave: true,
        "sAjaxSource": "/admin_zarz_dostep/get",
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
            {data: "dzialkowicz.nrDzialkowicza"},
            {data: "login"},
            {data: "password"},
            {data: "enabled"},
            {data: "role"}
        ],
order: [ 1, 'asc' ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        idSrc: "dzialkowicz.nrDzialkowicza",
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
                "dzialkowicz.nrDzialkowicza": $('#dzialkowicz.nrDzialkowicza').val(),
                "login": $('#login').val(),
          "password": $('#password').val(),
          "enabled": $('#enabled').val(),
                "role": $('#role').val()
                                            
            });
}


