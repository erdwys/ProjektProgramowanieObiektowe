               var editor;

                                $(document).ready(function () {


                                    editor = new $.fn.dataTable.Editor({

                                         ajax: {
                                                        create: {
           		
                                                url: '/admin_zarz_dzialka/add',                                      
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
                                                             url: "/admin_zarz_dzialka/update",
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
                                                        remove: {
                                                
                                                                type: 'DELETE',
                                                               url: "/admin_zarz_dzialka/delete",
                                                dataType: 'json',
                                                contentType: 'application/json; charset=utf-8',
                                              
                                             data: function (d) {
                                                    var obj;
                                                    for (var key in d.data) {
                                                        obj = d.data[key];
                                                        break;
                                                    }
                                                    JSON.stringify(obj);
                                                    return {"id" : obj["nrDzialki"]};
                                                },
                                          success: function (data) {
                                                    table.clear().draw();
                                                    table.ajax.reload();
                                                },
                                                error: function (e) {
                                                      table.clear().draw();
                                                    table.ajax.reload();
                                                }            

                                            }
                                                },
                                        "table": "#admin_zarz_dzialka_Table",
                                        idSrc: "nrDzialki",
                                        "fields": [{
                                                "label": "Nr Działki",
                                                "name": "nrDzialki"
                                            }, {
                                                   "label": "Powierzchnia",
                                                "name": "powierzchnia"
                                            }, {
                                               "label": "Nr członkowski",
                                                "name": "dzialkowicz.nrDzialkowicza"
                                            }
                                        ]
                                    });




                                    var table = $('#admin_zarz_dzialka_Table').DataTable({
                                          dom: "Bfrtip",
                                        "processing": true,
                                        "serverSide": false,
                                        "sAjaxSource": "/admin_zarz_dzialka/get",
                                        "sAjaxDataProp": "",
                                       
                                          columns: [
                                            {data: "nrDzialki"},
                                            {data: "powierzchnia"},
                                            {data: "dzialkowicz.nrDzialkowicza"}
                                        ],

                                        select: true,
                                        idSrc: "nrDzialki",
                                                buttons: [

                                                        {extend: "create", editor: editor},
                                                        {extend: "edit",   editor: editor},
                                                        {extend: "remove", editor: editor},
                                            {extend: 'pdfHtml5', orientation: 'landscape',
                pageSize: 'LEGAL',  download: 'open'}
                                                ]
                                    });
                                });

// Helper function to serialize all the form fields into a JSON string
                                function formToJSON() {
                                        return JSON.stringify({
                                                "nrDzialki": $('#nrDzialki').val(),
                                                "powierzchnia": $('#powierzchnia').val(),
                                                "dzialkowicz[].nrDzialkowicza": $('#dzialkowicz.nrDzialkowicza').val()
                                            
                                            });
                                }
                            
