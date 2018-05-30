package com.example;

import com.example.model.Informacja;
import com.example.model.Dostep;
import com.example.model.Iban;
import com.example.model.Zobowiazania;
import com.example.model.OdczytLicznika;
import com.example.model.WyciagiJs;
import com.example.model.Dzialkowicz;
import com.example.model.Dzialki;
import com.example.model.imp.ImplDostep;
import com.example.model.imp.ImplIban;
import com.example.model.imp.ImplZobowiazania;
import com.example.model.imp.ImplDzialki;
import com.example.model.imp.ImplDzialkowicz;
import com.example.model.imp.ImplWyciagiJs;
import com.example.model.imp.ImplOdczytLicznika;
import com.example.model.imp.ImplInformacja;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JFileChooser;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Filip
 */
public class DataMigrationExcelToDatabase {

    public static String ChooserFile() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            return selectedFile.getAbsolutePath();
        }
        return null;

    }

    public static void hqlTruncate(String myTable) {
        String hql = String.format("delete from %s", myTable);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.close();
    }

    public static void readFromExcelAndSaveToDatabase(FileInputStream file) throws IOException, Exception {

        try (HSSFWorkbook myExcelBook = new HSSFWorkbook(file)) {
            HSSFSheet myExcelSheet = myExcelBook.getSheet("Działki");
            HSSFRow row;
            Iterator<Row> rowIterator = myExcelSheet.iterator();
            int counterNull = 0;

            hqlTruncate("AktualizacjaDanych");
            hqlTruncate("Zobowiazania");
            hqlTruncate("WyciagiJs");
            hqlTruncate("OdczytLicznika");
            hqlTruncate("Informacja");
            hqlTruncate("Iban");
            hqlTruncate("Dostep");
            hqlTruncate("Dzialki");
            hqlTruncate("Dzialkowicz");

            if (myExcelSheet.getSheetName().equals("Działki")) {

                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    Dzialki dzialki = new Dzialki();

                    ImplDzialki impdzialki = new ImplDzialki();

                    Dzialkowicz dzialkowicz = new Dzialkowicz();

                    ImplDzialkowicz impdzialkowicz = new ImplDzialkowicz();

                    Dostep dost = new Dostep();
                    ImplDostep impdost = new ImplDostep();

                    if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        int nr_czlonkowski = (int) row.getCell(2).getNumericCellValue();
                        dzialkowicz.setNrDzialkowicza(Long.valueOf(String.valueOf(nr_czlonkowski)));
                        dzialki.setDzialkowicz(dzialkowicz);
                        dost.setDzialkowicz(dzialkowicz);

                        //System.out.println("nr_czlonkowski :" + nr_czlonkowski);
                        if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String imie = row.getCell(3).getStringCellValue();
                            dzialkowicz.setImie(imie);
                            //System.out.println("imie :" + imie);
                        }
                        if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String nazwisko = row.getCell(4).getStringCellValue();
                            dzialkowicz.setNazwisko(nazwisko);
                            //System.out.println("nazwisko :" + nazwisko);
                        }
                        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String ulica = row.getCell(5).getStringCellValue();
                            dzialkowicz.setUlica(ulica);
                            //System.out.println("ulica :" + ulica);
                        }
                        if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING || row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String nr_domu = row.getCell(6).getStringCellValue();
                            dzialkowicz.setNrDomu(nr_domu);
                            //System.out.println("nr_domu :" + nr_domu);
                        }
                        if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_STRING || row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String nr_lokalu = row.getCell(7).getStringCellValue();
                            dzialkowicz.setNrLokalu(nr_lokalu);
                            //System.out.println("nr_lokalu :" + nr_lokalu);
                        }
                        if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_STRING || row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String kod_pocztowy = row.getCell(8).getStringCellValue();
                            dzialkowicz.setKodPocztowy(kod_pocztowy);
                            //System.out.println("kod_pocztowy :" + kod_pocztowy);
                        }
                        if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String miasto = row.getCell(9).getStringCellValue();
                            dzialkowicz.setMiasto(miasto);
                            //System.out.println("miasto :" + miasto);
                        }
                        if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String adres_do_koresp = row.getCell(10).getStringCellValue();
                            dzialkowicz.setAdresDoKorespondencji(adres_do_koresp);
                            //System.out.println("adres_do_koresp :" + adres_do_koresp);
                        }
                        if (row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int telefon = (int) row.getCell(11).getNumericCellValue();
                            dzialkowicz.setTelefon(telefon);
                            //System.out.println("telefon :" + telefon);
                        }
                        if (row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String email = row.getCell(12).getStringCellValue();
                            dzialkowicz.setEmail(email);
                            dost.setLogin(email);
                            //System.out.println("email :" + email);
                        } else {
                            dzialkowicz.setEmail("dzialkowicz" + String.valueOf(dzialkowicz.getNrDzialkowicza()) + "@dzialki");

                            dost.setLogin("dzialkowicz" + String.valueOf(dzialkowicz.getNrDzialkowicza()) + "@dzialki");

                            //System.out.println("email :" + "dzialkowicz"+String.valueOf(dzialkowicz.getNrDzialkowicza())+"@dzialki");
                        }

                        if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                            dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                            //System.out.println("nr_dzialki : " + nr_dzialki);
                        }
                        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int powierzchnia = (int) row.getCell(1).getNumericCellValue();
                            dzialki.setPowierzchnia(powierzchnia);
                            //System.out.println("powierzchnia :" + powierzchnia);
                        }

                        String password = RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphanumeric(6).toLowerCase() + nr_czlonkowski;
                        dost.setPassword(password);

                        impdzialkowicz.add(dzialkowicz);
                        impdzialki.add(dzialki);
                        impdost.add(dost);

                    } else {
                        counterNull++;
                    }
                }

            }

            myExcelSheet = myExcelBook.getSheet("IBAN");

            rowIterator = myExcelSheet.iterator();
            counterNull = 0;

            if (myExcelSheet.getSheetName().equals("IBAN")) {
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    Iban iban = new Iban();

                    ImplIban impiban = new ImplIban();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        iban.setDzialki(dzialki);
                        //System.out.println("nr_dzialki :" + nr_dzialki);

                        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            String ibann = row.getCell(1).getStringCellValue();
                            iban.setKodIban(ibann);
                            //System.out.println("iban :" + ibann);
                        }
                        if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            String nr_konta = row.getCell(2).getStringCellValue();
                            iban.setNrKonta(nr_konta);
                            //System.out.println("nr_konta :" + nr_konta);
                        }

                        impiban.add(iban);
                    } else {
                        counterNull++;
                    }
                }
            }
            myExcelSheet = myExcelBook.getSheet("Informacja");

            rowIterator = myExcelSheet.iterator();
            counterNull = 0;
            if (myExcelSheet.getSheetName().equals("Informacja")) {
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    Informacja informacja = new Informacja();
                    ImplInformacja impinformacja = new ImplInformacja();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        informacja.setDzialki(dzialki);
                        informacja.setNrInformacji(1);
                        informacja.setRokRozliczeniowy(2017);
                        //System.out.println("nr_dzialki :" + nr_dzialki);

                        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double stan_rozliczenia = row.getCell(5).getNumericCellValue();
                            informacja.setStanRozliczenia(stan_rozliczenia);
                            //System.out.println("stan_rozliczenia :" + stan_rozliczenia);
                        }
                        if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            String wiadomosc = row.getCell(6).getStringCellValue();
                            informacja.setInformacja(wiadomosc);
                            //System.out.println("wiadomosc :" + wiadomosc);
                        }

                        informacja.setNadawca("ADMIN");

                        impinformacja.add(informacja);
                    } else {
                        counterNull++;
                    }
                }

            }
            myExcelSheet = myExcelBook.getSheet("Odczyty liczn");

            rowIterator = myExcelSheet.iterator();
            counterNull = 0;
            if (myExcelSheet.getSheetName().equals("Odczyty liczn")) {
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    OdczytLicznika odczytLicznika = new OdczytLicznika();
                    ImplOdczytLicznika impodczytLicznika = new ImplOdczytLicznika();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        odczytLicznika.setDzialki(dzialki);
                        odczytLicznika.setNrPomiaru(0);
                        odczytLicznika.setData("2016-09-11");
                        //System.out.println("nr_dzialki :" + nr_dzialki);
                        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int stan_licznika = (int) row.getCell(1).getNumericCellValue();
                            odczytLicznika.setStanLicznika(stan_licznika);
                            odczytLicznika.setNaleznosc(0.0);
                            //System.out.println("stan_licznika :" + stan_licznika);

                        }
                        impodczytLicznika.add(odczytLicznika);

                    }
                }
                rowIterator = myExcelSheet.iterator();
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    OdczytLicznika odczytLicznika = new OdczytLicznika();
                    ImplOdczytLicznika impodczytLicznika = new ImplOdczytLicznika();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        odczytLicznika.setDzialki(dzialki);
                        odczytLicznika.setNrPomiaru(1);
                        odczytLicznika.setData("2017-06-01");
                        //System.out.println("nr_dzialki :" + nr_dzialki);
                        if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int stan_licznika = (int) row.getCell(2).getNumericCellValue();
                            odczytLicznika.setStanLicznika(stan_licznika);
                            //System.out.println("stan_licznika :" + stan_licznika);

                        }
                        if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double naleznosc = row.getCell(3).getNumericCellValue();
                            odczytLicznika.setNaleznosc(naleznosc);
                            //System.out.println("naleznosc :" + naleznosc);

                        }

                        impodczytLicznika.add(odczytLicznika);

                    }
                }
                rowIterator = myExcelSheet.iterator();
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }
                    row = (HSSFRow) rowIterator.next();

                    OdczytLicznika odczytLicznika = new OdczytLicznika();
                    ImplOdczytLicznika impodczytLicznika = new ImplOdczytLicznika();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        odczytLicznika.setDzialki(dzialki);
                        odczytLicznika.setNrPomiaru(2);
                        odczytLicznika.setData("2017-09-24");
                        //System.out.println("nr_dzialki :" + nr_dzialki);
                        if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int stan_licznika = (int) row.getCell(4).getNumericCellValue();
                            odczytLicznika.setStanLicznika(stan_licznika);
                            //System.out.println("stan_licznika :" + stan_licznika);

                        }
                        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double naleznosc = row.getCell(5).getNumericCellValue();
                            odczytLicznika.setNaleznosc(naleznosc);
                            //System.out.println("naleznosc :" + naleznosc);

                        }

                        impodczytLicznika.add(odczytLicznika);

                    } else {
                        counterNull++;
                    }
                }

            }
            myExcelSheet = myExcelBook.getSheet("Wyciągi-JS");

            rowIterator = myExcelSheet.iterator();
            counterNull = 0;
            if (myExcelSheet.getSheetName().equals("Wyciągi-JS")) {
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    WyciagiJs wyciagiJs = new WyciagiJs();
                    ImplWyciagiJs impwyciagiJs = new ImplWyciagiJs();

                    if ((row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) && (int) row.getCell(1).getNumericCellValue() > 0) {
                        int nr_dzialki = (int) row.getCell(1).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        wyciagiJs.setDzialki(dzialki);
                        //System.out.println("nr_dzialki :" + nr_dzialki);

                        if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            int nr_wyciągu = (int) row.getCell(0).getNumericCellValue();
                            wyciagiJs.setNrWyciagu(nr_wyciągu);
                            //System.out.println("nr_wyciągu :" + nr_wyciągu);
                        } else {
                            wyciagiJs.setNrWyciagu(0);
                        }

                        if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_FORMULA || row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double kwota = row.getCell(2).getNumericCellValue();
                            wyciagiJs.setKwota(kwota);
                            //System.out.println("kwota :" + kwota);
                        }

                        if (DateUtil.isCellDateFormatted(row.getCell(3))) {
                            String data = String.valueOf(row.getCell(3).getDateCellValue());
                            SimpleDateFormat data_formatToExcel = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                            Date dataExcel = data_formatToExcel.parse(data);
                            data = new SimpleDateFormat("yyyy-MM-dd").format(dataExcel);
                            wyciagiJs.setData(data);
                            //System.out.println("data :" + data);
                        }
                        if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING || row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            String opis = row.getCell(4).getStringCellValue();
                            wyciagiJs.setOpis(opis);
                            //System.out.println("opis :" + opis);
                        }
                        ////////////////////////////
                        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double skladka = row.getCell(5).getNumericCellValue();
                            wyciagiJs.setSkladka(skladka);
                            //System.out.println("skladka :" + skladka);
                        }
                        if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double czynsz = row.getCell(6).getNumericCellValue();
                            wyciagiJs.setCynsz(czynsz);
                            //System.out.println("czynsz :" + czynsz);
                        }
                        if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double awrbp = row.getCell(7).getNumericCellValue();
                            wyciagiJs.setAwrbp(awrbp);
                            //System.out.println("awrbp :" + awrbp);
                        }
                        if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double wpisowe = row.getCell(8).getNumericCellValue();
                            wyciagiJs.setWpisowe(wpisowe);
                            //System.out.println("wpisowe :" + wpisowe);
                        }
                        if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double ene1 = row.getCell(9).getNumericCellValue();
                            wyciagiJs.setEnergiaRozpoczecieSezonu(ene1);
                            //System.out.println("ene1 :" + ene1);
                        }
                        if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double ene2 = row.getCell(10).getNumericCellValue();
                            wyciagiJs.setEnergiaZakonczenieSezonu(ene2);
                            //System.out.println("ene2 :" + ene2);
                        }
                        if (row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double dyzur1 = row.getCell(11).getNumericCellValue();
                            wyciagiJs.setDyzurZRokuPoprzedniegoNaBiezacy(dyzur1);
                            //System.out.println("dyzur1 :" + dyzur1);
                        }
                        if (row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double dyzur2 = row.getCell(12).getNumericCellValue();
                            wyciagiJs.setDyzurZRokuBiezacegoNaNastepny(dyzur2);
                            //System.out.println("dyzur2 :" + dyzur2);
                        }
                        if (row.getCell(13).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double zadluzenie = row.getCell(13).getNumericCellValue();
                            wyciagiJs.setZadluzenieZRokuPoprzedniego(zadluzenie);
                            //System.out.println("zadluzenie :" + zadluzenie);
                        }
                        if (row.getCell(14).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double licznik = row.getCell(14).getNumericCellValue();
                            wyciagiJs.setLicznik(licznik);
                            //System.out.println("licznik :" + licznik);
                        }

                        impwyciagiJs.add(wyciagiJs);
                    } else {
                        counterNull++;
                    }
                }

            }
            myExcelSheet = myExcelBook.getSheet("Wynik");

            rowIterator = myExcelSheet.iterator();
            counterNull = 0;
            if (myExcelSheet.getSheetName().equals("Wynik")) {
                while (rowIterator.hasNext()) {

                    if (counterNull > 100) {
                        break;
                    }

                    row = (HSSFRow) rowIterator.next();

                    Zobowiazania zobowiazania = new Zobowiazania();
                    ImplZobowiazania impzobowiazania = new ImplZobowiazania();

                    if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        int nr_dzialki = (int) row.getCell(0).getNumericCellValue();
                        Dzialki dzialki = new Dzialki();
                        dzialki.setNrDzialki(Long.valueOf(String.valueOf(nr_dzialki)));
                        zobowiazania.setDzialki(dzialki);
                        zobowiazania.setRokRozliczeniowy(2017);
                        //System.out.println("nr_dzialki :" + nr_dzialki);

                        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double bilans = row.getCell(1).getNumericCellValue();
                            zobowiazania.setBilansOtwarcia(bilans);
                            //System.out.println("bilans :" + bilans);
                        }
                        if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double składka = row.getCell(2).getNumericCellValue();
                            zobowiazania.setSkladka(składka);
                            //System.out.println("składka :" + składka);
                        }
                        if (row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double czynsz = row.getCell(3).getNumericCellValue();
                            zobowiazania.setCzynsz(czynsz);
                            //System.out.println("czynsz :" + czynsz);
                        }
                        if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double anr = row.getCell(4).getNumericCellValue();
                            zobowiazania.setAnr(anr);
                            //System.out.println("anr :" + anr);
                        }
                        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double wpisowe = row.getCell(5).getNumericCellValue();
                            zobowiazania.setWpisowe(wpisowe);
                            //System.out.println("wpisowe :" + wpisowe);
                        }
                        //////////
                        if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double ene1 = row.getCell(6).getNumericCellValue();
                            zobowiazania.setEnergiaRozpocecieSezonu(ene1);
                            //System.out.println("ene1 :" + ene1);
                        }
                        if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double ene2 = row.getCell(7).getNumericCellValue();
                            zobowiazania.setEnergiaZakonczeniaSeoznu(ene2);
                            //System.out.println("ene2 :" + ene2);
                        }
                        if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double dyzur1 = row.getCell(8).getNumericCellValue();
                            zobowiazania.setDyzurZRokuPoprzedniegoNaBiezacy(dyzur1);
                            //System.out.println("dyzur1 :" + dyzur1);
                        }
                        if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            Double dyzur2 = row.getCell(9).getNumericCellValue();
                            zobowiazania.setDyzurZRokuBiezacegoNaNastepny(dyzur2);
                            //System.out.println("dyzur2 :" + dyzur2);
                        }
                        if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double zadluz = row.getCell(10).getNumericCellValue();
                            zobowiazania.setZadluzenieZRokuPoprzedniego(zadluz);
                            //System.out.println("zadluz :" + zadluz);
                        }
                        if (row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                            Double zoboBO = row.getCell(11).getNumericCellValue();
                            zobowiazania.setZobowiazaniaRazemZBo(zoboBO);
                            //System.out.println("zoboBO :" + zoboBO);
                        }

                        impzobowiazania.add(zobowiazania);
                    } else {
                        counterNull++;
                    }

                }
            }

            myExcelBook.close();

        }

    }

}
