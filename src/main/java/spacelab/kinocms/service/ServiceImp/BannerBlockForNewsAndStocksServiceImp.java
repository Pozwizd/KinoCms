package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.BannerBlockForNewsAndStocks;
import spacelab.kinocms.repository.BannerBlockForNewsAndStocksRepository;
import spacelab.kinocms.service.BannerBlockForNewsAndStocksService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerBlockForNewsAndStocksServiceImp implements BannerBlockForNewsAndStocksService {

    private final BannerBlockForNewsAndStocksRepository bannerBlockForNewsAndStocksRepository;

    @Override
    public void saveBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks) {

    }

    @Override
    public List<BannerBlockForNewsAndStocks> getAllBannerBlockForNewsAndStocks() {
        return bannerBlockForNewsAndStocksRepository.findAll();
    }

    @Override
    public void deleteBannerBlockForNewsAndStocks(long id) {
        bannerBlockForNewsAndStocksRepository.deleteById(id);
    }

    @Override
    public BannerBlockForNewsAndStocks getBannerBlockForNewsAndStocks(long id) {
        return bannerBlockForNewsAndStocksRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBannerBlockForNewsAndStocks(BannerBlockForNewsAndStocks bannerBlockForNewsAndStocks) {
        bannerBlockForNewsAndStocksRepository.save(bannerBlockForNewsAndStocks);
    }
}
