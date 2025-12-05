import { ICategory, NewCategory } from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 8109,
  name: 'midst croon cautiously',
  type: 'INCOME',
};

export const sampleWithPartialData: ICategory = {
  id: 862,
  name: 'meanwhile',
  description: 'summarise far consequently',
  type: 'INCOME',
};

export const sampleWithFullData: ICategory = {
  id: 28780,
  name: 'ouch',
  description: 'self-confidence',
  type: 'INCOME',
};

export const sampleWithNewData: NewCategory = {
  name: 'where pfft regularly',
  type: 'EXPENSE',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
