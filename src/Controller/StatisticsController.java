package Controller;

import java.util.List;

import Common.ValidationException;
import DomainObjects.StatisticsDomainObject;
import DomainModels.StatisticsDomainModel;
import restService.Request.CreateStatisticsRequest;
import restService.Response.CreateStatisticsResponse;

public class StatisticsController {

    public static CreateStatisticsResponse getStatistics(CreateStatisticsRequest request) {
        try {
            StatisticsDomainObject statistics = StatisticsDomainModel.getStatistics(request.getPlayerId());
            return new CreateStatisticsResponse(statistics);
        } catch (ValidationException vex) {
            return new CreateStatisticsResponse(vex.getErrorMessages());
        } catch (Exception ex) {
            return new CreateStatisticsResponse(
                    List.of("An error occurred while retrieving statistics: " + ex.getMessage()));
        }
    }
}
