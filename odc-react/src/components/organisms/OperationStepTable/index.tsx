import { Paper, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router'
import { HeaderKeyPair, OperationStepTableProps, DataRowType } from '../../../shared/types/types'

const OPERATIONSTEPS_HEADER_KEY_TEMPLATE: HeaderKeyPair[] = [
    { key: "category", headerName: "Category" },
    { key: "operatorName", headerName: "Operator" },
    { key: "startTime", headerName: "Start" },
    { key: "stopTime", headerName: "Ende" },
    { key: "status", headerName: "Status" }
]

const StyledTableHeaderCell = styled(TableCell)({
    backgroundColor: "orange",
    fontWeight: "900",
    color: "white",
})

const StyledTableRow = styled(TableRow)({
    ":hover": {
        "background": "lightgrey"
    }
})

const StyledTableCell = styled(TableCell)({
    fontSize: "1em"
})



const OperationStepTable: React.FC<OperationStepTableProps> = (props) => {

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "500px" }}>
            <Table stickyHeader>
                <TableHead>
                    <TableRow>
                        {OPERATIONSTEPS_HEADER_KEY_TEMPLATE.map(headerKeyPair =>
                            <StyledTableHeaderCell key={headerKeyPair.headerName}>{headerKeyPair.headerName}</StyledTableHeaderCell>)}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((dataRow: DataRowType) => {
                        return (
                            <StyledTableRow key={dataRow.id} >
                                {OPERATIONSTEPS_HEADER_KEY_TEMPLATE.map(keyHeaderPair =>
                                    <StyledTableCell key={keyHeaderPair.key}>{dataRow[keyHeaderPair.key]}</StyledTableCell>)}
                            </StyledTableRow>
                        )
                    })
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}

export default OperationStepTable