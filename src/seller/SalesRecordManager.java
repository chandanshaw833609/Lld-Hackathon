package seller;

import book.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesRecordManager {
    private static SalesRecordManager instance;
    private final Map<String, SalesRecord> salesRecordMap;

    private SalesRecordManager() {
        salesRecordMap = new HashMap<>();
    }

    public static SalesRecordManager getInstance() {
        if (instance == null) {
            instance = new SalesRecordManager();
        }
        return instance;
    }

    public void saveSalesRecord(SalesRecord record) {
        salesRecordMap.put(record.getId(), record);
    }

    public List<SalesRecord> getSalesRecordBySeller(String sellerId) {
        return salesRecordMap.values()
                .stream()
                .filter(salesRecord -> salesRecord.getSellerId().equals(sellerId))
                .toList();
    }

    public SalesRecord getSalesRecordBySellerAndBook(String sellerId, String bookId) {
        return salesRecordMap.values()
                .stream()
                .filter(salesRecord -> salesRecord.getSellerId().equals(sellerId))
                .filter(salesRecord -> salesRecord.getBook().getBookId().equals(bookId))
                .findFirst().orElse(null);
    }
}
