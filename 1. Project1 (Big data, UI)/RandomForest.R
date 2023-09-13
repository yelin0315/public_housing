# 라이브러리 설치 및 불러오기
install.packages("randomForest")
library(randomForest)

# 데이터 불러오기
data <- read.csv("/Users/Administrator/Desktop/competition.csv")

# 결측치 처리 (중앙값으로 대체)
data$size <- ifelse(is.na(data$size), median(data$size, na.rm = TRUE), data$size)
data$people <- ifelse(is.na(data$people), median(data$people, na.rm = TRUE), data$people)
data$rate <- ifelse(is.na(data$rate), median(data$rate, na.rm = TRUE), data$rate)
data$value <- ifelse(is.na(data$value), median(data$value, na.rm = TRUE), data$value)
data$d.sub <- ifelse(is.na(data$d.sub), median(data$d.sub, na.rm = TRUE), data$d.sub)
data$security <- ifelse(is.na(data$security), median(data$security, na.rm = TRUE), data$security)
data$avg.price <- ifelse(is.na(data$avg.price), median(data$avg.price, na.rm = TRUE), data$avg.price)
data$supply <- ifelse(is.na(data$supply), median(data$supply, na.rm = TRUE), data$supply)
data$cut <- ifelse(is.na(data$cut), median(data$cut, na.rm = TRUE), data$cut)
data <- na.omit(data)

for (col in names(trainData)) {
  trainData[[col]][is.na(trainData[[col]])] <- mean(trainData[[col]], na.rm = TRUE)
}

for (col in names(testData)) {
  testData[[col]][is.na(testData[[col]])] <- mean(testData[[col]], na.rm = TRUE)
}



# 데이터 분할
set.seed(42)
trainIndex <- sample(1:nrow(data), 0.8*nrow(data))
trainData <- data[trainIndex,]
testData <- data[-trainIndex,]

# 랜덤포레스트 모델 학습
rf_model <- randomForest(cut ~  d.hos + size  + rate + value + d.sub + security + avg.price + supply, data=trainData, ntree=100)

# 예측
predictions <- predict(rf_model, newdata=testData)

results <- data.frame(region = testData$region, Predicted = predictions)

# 지역별 예측값의 평균
region_avg_predictions <- aggregate(Predicted ~ region, data = results, FUN = mean)

# 결과 출력
print(region_avg_predictions)

# 성능 평가 (RMSE)
rmse <- sqrt(mean((predictions - testData$cut)^2))

print(rmse)

# 특성 중요도 확인
importance(rf_model)
