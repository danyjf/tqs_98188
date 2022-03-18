import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks;
    private IStockMarketService stockMarketService;

    public StocksPortfolio(IStockMarketService stockMarketService) {
        stocks = new ArrayList<Stock>();
        this.stockMarketService = stockMarketService;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double total = 0.0;

        for(Stock stock : stocks) {
            total += (stockMarketService.lookUpPrice(stock.getLabel()) * stock.getQuantity());
        }

        return total;
    }
}
