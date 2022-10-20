import { useQuery } from 'react-query'
import * as yup from 'yup'
import { AnyObject, ObjectShape, OptionalObjectSchema, TypeOfShape } from 'yup/lib/object'
import { getOperationInformation } from '../../dataProvider/api'

const useAddStepFormSchema = (): undefined | OptionalObjectSchema<ObjectShape, AnyObject, TypeOfShape<ObjectShape>> => {
    const { data: information, isSuccess } = useQuery(['information'], () => getOperationInformation())

    if (isSuccess) {
        const schema = yup.object().shape({
            operatorName: yup.string().test(
                'operatorName',
                'not a valid operator name',
                (value) => {
                    return value && information.operatorNames ? information.operatorNames.includes(value) : false;
                }),
            batchId: yup.string().test(
                'batchId',
                'not a valid active batchId',
                (value) => {
                    return value && information.activeBatchIds ? information.activeBatchIds.includes(value) : false;
                })
        })

        return schema
    }
    return undefined;

}

export default useAddStepFormSchema