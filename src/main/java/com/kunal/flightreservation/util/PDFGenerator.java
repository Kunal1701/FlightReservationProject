package com.kunal.flightreservation.util;

import com.kunal.flightreservation.entities.Reservation;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class PDFGenerator {
    public void generateItinerary(Reservation reservation, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(generateTable(reservation));
            document.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private PdfPTable generateTable(Reservation reservation) {
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Flight Itinerary"));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Departure City");
        table.addCell(reservation.getFlight().getDepartureCity());
        table.addCell("Arrival City");
        table.addCell(reservation.getFlight().getArrivalCity());
        table.addCell("Flight Number");
        table.addCell(reservation.getFlight().getFlightNumber());
        table.addCell("Operating Airline");
        table.addCell(reservation.getFlight().getOperatingAirlines());
        table.addCell("Date of Departure");
        table.addCell(reservation.getFlight().getDateOfDeparture().toString());
        table.addCell("Estimated Departure Time");
        table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
        cell = new PdfPCell(new Phrase("Passenger Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("First Name");
        table.addCell(reservation.getPassenger().getFirstName());
        table.addCell("Last Name");
        table.addCell(reservation.getPassenger().getLastName());
        table.addCell("Email");
        table.addCell(reservation.getPassenger().getEmail());
        table.addCell("Phone");
        table.addCell(reservation.getPassenger().getPhone());


        return table;

    }
}
