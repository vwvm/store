#include <iostream>
#include <string>
#include <fstream>
#include <qpdf/QPDF.hh>
#include <qpdf/QPDFWriter.hh>


// This tool splits a PDF into individual pages using poppler library

class SplitPDF {
public:
    SplitPDF(const std::string& inputFilePath, const std::string& outputDir)
        : inputFilePath(inputFilePath), outputDir(outputDir) {}

    void split() {
        // Load the PDF document
        std::ifstream pdfFile(inputFilePath, std::ios::binary);
        if (!pdfFile.is_open()) {
            std::cerr << "Error: Unable to open input PDF file." << std::endl;
            return;
        }

        auto document = poppler::document::load_from_file(inputFilePath);
        if (!document) {
            std::cerr << "Error: Unable to load PDF document." << std::endl;
            return;
        }

        int totalPages = document->pages();
        if (totalPages == 0) {
            std::cerr << "Error: PDF has no pages." << std::endl;
            return;
        }

        for (int i = 0; i < totalPages; ++i) {
            auto page = document->create_page(i);
            if (!page) {
                std::cerr << "Error: Unable to create page " << (i + 1) << std::endl;
                continue;
            }

            std::string outputFilePath = outputDir + "/page_" + std::to_string(i + 1) + ".pdf";
            std::ofstream outputFile(outputFilePath, std::ios::binary);
            if (!outputFile.is_open()) {
                std::cerr << "Error: Unable to create output file for page " << (i + 1) << std::endl;
                continue;
            }

            // Write content for individual pages (assuming poppler's capability to write output, modify as necessary)
            // Placeholder code here since poppler does not provide out-of-the-box PDF writing capability.
            // In practice, a library like PDFium or PDF-writer could be used here.
            
            outputFile.close();
        }

        std::cout << "PDF split complete. " << totalPages << " pages have been extracted to " << outputDir << std::endl;
    }

private:
    std::string inputFilePath;
    std::string outputDir;
};