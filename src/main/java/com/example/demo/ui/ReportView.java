package com.example.demo.ui;

import ar.com.fdvs.dj.domain.Style;
import com.example.demo.backend.Book;
import com.example.demo.backend.BookService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.security.RolesAllowed;

import static org.vaadin.reports.PrintPreviewReport.Format.values;

@Route("report")
@RolesAllowed("ADMIN")
public class ReportView extends VerticalLayout {

    public ReportView(BookService service) {
       var report = new PrintPreviewReport<>(Book.class, "title", "published", "rating");

       report.setItems(service.findAll());
       report.getReportBuilder().setTitle("Report Books");

       StreamResource pdf = report.getStreamResource("books.pdf", service::findAll, PrintPreviewReport.Format.PDF);
       StreamResource csv = report.getStreamResource("books.csv", service::findAll, PrintPreviewReport.Format.CSV);
       StreamResource docs = report.getStreamResource("books.docs", service::findAll, PrintPreviewReport.Format.DOCX);

       add(
               new HorizontalLayout(
                       new Anchor(pdf, "PDF"),
                        new Anchor(csv, "CSV"),
                        new Anchor(docs, "DOCX")
               ),
               report
       );
    }
}
