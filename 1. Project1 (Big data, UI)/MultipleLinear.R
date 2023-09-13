---
title: "REGRESSION"
date: '2023-07-14'
---

```{r setup, include=FALSE}
library(writexl)
library(randomForest)
library(glmnet)
library(MASS)
```

## 데이터 전처리
```{r home}
data <- read.csv("/Users/Administrator/Desktop/competition.csv")
summary(data)

# 결측치 처리 
# 다중회귀분석은 연속 변수만 사용 가능
data$size <- as.numeric(data$size)
data$people <- as.numeric(data$people)

# 결측값은 평균치로 대체
data$size <- ifelse(is.na(data$size), mean(data$size, na.rm = TRUE), data$size)
data$people <- ifelse(is.na(data$people), mean(data$people, na.rm = TRUE), data$people)
data$rate <- ifelse(is.na(data$rate), mean(data$rate, na.rm = TRUE), data$rate)
data$value <- ifelse(is.na(data$value), mean(data$value, na.rm = TRUE), data$value)
data$d.sub <- ifelse(is.na(data$d.sub), mean(data$d.sub, na.rm = TRUE), data$d.sub)
data$d.hos <- ifelse(is.na(data$d.hos), mean(data$d.hos, na.rm = TRUE), data$d.hos)
data$cut <- ifelse(is.na(data$cut), mean(data$cut, na.rm = TRUE), data$cut)
data$supply <- ifelse(is.na(data$supply), mean(data$supply, na.rm = TRUE), data$supply)

summary(data)
sum(is.na(data))

testset <- data[, c("d.hos","size","people","rate","value","d.sub","security","avg.price", "supply","region")]
testset2 <- data[, c("d.hos","size","people","rate","value","d.sub","security","avg.price", "supply")]
summary(testset)


# 상관관계, 다중회귀분석
options(scipen = 999)

# 상관계수 계산
cor_matrix <- cor(testset)
pval_matrix <- matrix(NA, nrow = ncol(cor_matrix), ncol = ncol(cor_matrix))

# 상관계수와 p-value 계산
for (i in 1:(ncol(cor_matrix) - 1)) {
  for (j in (i + 1):ncol(cor_matrix)) {
    cor_test <- cor.test(testset[, i], testset[, j])
    cor_matrix[i, j] <- cor_matrix[j, i] <- cor_test$estimate
    pval_matrix[i, j] <- pval_matrix[j, i] <- cor_test$p.value
  }
}

# 상관계수와 p-value 출력
var_names <- colnames(cor_matrix)
for (i in 1:(ncol(cor_matrix) - 1)) {
  for (j in (i + 1):ncol(cor_matrix)) {
    var1 <- var_names[i]
    var2 <- var_names[j]
    cor_val <- cor_matrix[i, j]
    p_val <- pval_matrix[i, j]
    print(paste("Correlation between", var1, "and", var2, ":"))
    print(paste("Correlation:", cor_val))
    print(paste("p-value:", p_val))
    print("-------------------------------------")
  }
}

# 다중회귀분석
model1 <- lm(rate ~ d.hos + size + people + value + d.sub + security + avg.price + 
    supply, data = testset)
summary(model1)




unique(data$region)

# 지역 목록 생성
regions <- unique(data$region)


# 각 지역에 대한 평균 예측값 계산 및 출력
for (region_name in regions) {
  # 해당 지역의 데이터 추출
  region_df <- subset(data, region == region_name)
  
  # 모델을 사용하여 예측 수행
  predicted_values <- predict(model1, newdata = region_df)
  
  # 평균 예측값 계산
  average_predicted_value <- mean(predicted_values)
  
  # 지역별 평균 예측값 출력 (소수점 2자리까지 출력)
  cat(region_name, "지역의 예측값:", round(average_predicted_value, 2), "\n")
}

# 범주형 독립 변수에 대한 분산 분석 수행
aov_model <- aov(rate ~  region, data)
summary(aov_model)


# AIC 회귀분석
library(MASS)
model <- lm(rate ~ ., data = testset2)
step_model <- stepAIC(model, direction = "both")
summary(step_model)

# 능형 회귀분석
library(glmnet)
x <- testset[, -3] 
y <- testset$rate

lasso_model <- glmnet(x, y, alpha = 1)


# 회귀계수 출력
coef(lasso_model) # 0이 아니면 모두 유의

