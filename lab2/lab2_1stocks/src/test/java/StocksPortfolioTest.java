import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {
    @Mock
    IStockMarketService mockStockMarketService;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {
        portfolio.addStock(new Stock("stock1", 2));
        portfolio.addStock(new Stock("stock2", 1));

        Mockito.when(mockStockMarketService.lookUpPrice("stock1")).thenReturn(12.2);
        Mockito.when(mockStockMarketService.lookUpPrice("stock2")).thenReturn(24.9);

        double total = 49.3;

        assertThat(portfolio.getTotalValue(), is(total));
        Mockito.verify(mockStockMarketService, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }
}
