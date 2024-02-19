package spacelab.kinocms.service.ServiceImp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spacelab.kinocms.model.BannerBlockForNewsAndStocks;
import spacelab.kinocms.model.BannerForNewsAndStocks;
import spacelab.kinocms.repository.BannerForNewsAndStocksRepository;
import spacelab.kinocms.service.BannerForNewsAndStocksService;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerForNewsAndStocksServiceImp implements BannerForNewsAndStocksService {

    private final BannerForNewsAndStocksRepository bannerForNewsAndStocksRepository;
    @Override
    public BannerForNewsAndStocks getBannerForNewsAndStocksById(Long id) {
        return bannerForNewsAndStocksRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks) {
        bannerForNewsAndStocksRepository.save(bannerForNewsAndStocks);
    }

    @Override
    public void deleteBannerForNewsAndStocks(Long id) {
        bannerForNewsAndStocksRepository.deleteById(id);
    }

    @Override
    public void updateBannerForNewsAndStocks(BannerForNewsAndStocks bannerForNewsAndStocks) {
        bannerForNewsAndStocksRepository.save(bannerForNewsAndStocks);
    }

    @Override
    public List<BannerForNewsAndStocks> getAllBannerForNewsAndStocks() {
        return bannerForNewsAndStocksRepository.findAll();
    }

    @Override
    public BannerForNewsAndStocks getLastBannerForNewsAndStocks() {
        return bannerForNewsAndStocksRepository
                .findLastBannerForNewsAndStocks();
    }

}
