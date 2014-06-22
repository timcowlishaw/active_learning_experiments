# coding: utf-8

import sklearn
import statsmodels.api as sm
import matplotlib.pyplot as plt
import numpy as np
from sklearn import datasets
from sklearn import cross_validation
digits = datasets.load_digits(2)
data = digits['data']
target = digits['target']
data_norm = sklearn.preprocessing.normalize(data)
U, s, V = np.linalg.svd(data_norm)
eigvals = s**2/np.cumsum(s)[-1]
num_vars =64
sing_vals = np.arange(num_vars) + 1
plt.plot(sing_vals, eigvals)
retain_dims=4
S = np.zeros((360, 64))
S[:64, :64] = np.diag(s)
np.allclose(data_norm, np.dot(U, np.dot(S, V)))
S_reduced = S[:360, :retain_dims]
V_reduced = V[:retain_dims, :retain_dims]
data_reduced = np.dot(U, np.dot(S_reduced, V_reduced))
X_train, X_test, y_train, y_test = cross_validation.train_test_split(data_reduced, target, test_size=0.4)
model = sm.Logit(y_train, X_train)
fit = model.fit_regularized()
y_pred = model.predict(fit.params, X_test)
decision_boundary = 0.5
y_pred_class = [1 if y > decision_boundary else 0 for y in y_pred]
print sklearn.metrics.classification_report(y_test, y_pred_class)
