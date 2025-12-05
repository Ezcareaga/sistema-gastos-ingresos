import dayjs from 'dayjs/esm';

import { ITransaction, NewTransaction } from './transaction.model';

export const sampleWithRequiredData: ITransaction = {
  id: 3793,
  date: dayjs('2025-12-04'),
  amount: 2419.29,
  type: 'EXPENSE',
};

export const sampleWithPartialData: ITransaction = {
  id: 26846,
  date: dayjs('2025-12-04'),
  amount: 11058.39,
  type: 'EXPENSE',
  reference: 'standard schlep',
};

export const sampleWithFullData: ITransaction = {
  id: 20285,
  date: dayjs('2025-12-04'),
  amount: 25161.85,
  type: 'EXPENSE',
  description: 'briefly boohoo talkative',
  reference: 'boohoo',
};

export const sampleWithNewData: NewTransaction = {
  date: dayjs('2025-12-04'),
  amount: 29048,
  type: 'EXPENSE',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
